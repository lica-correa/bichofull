package com.bichofull.backend.service;

import com.bichofull.backend.entity.Aposta;
import com.bichofull.backend.repository.ApostaRepository;
import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.repository.UsuarioRepository;
import com.bichofull.backend.entity.Animal;
import com.bichofull.backend.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public Aposta salvar(Aposta aposta){
        Usuario usuario = usuarioRepository.findById(aposta.getUsuario().getId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Animal animal = animalRepository.findById(aposta.getAnimal().getId()).orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        if (usuario.getSaldo() < aposta.getValor()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Saldo insuficiente"
            );
        }

        usuario.setSaldo(usuario.getSaldo() - aposta.getValor());
        usuarioRepository.save(usuario);

        aposta.setUsuario(usuario);
        aposta.setAnimal(animal);

    aposta.setData(java.time.LocalDate.now());

        return repository.save(aposta);
    }

    public List<Aposta> listar() {
        return repository.findAll();
    }

    public List<Aposta> buscarPorUsuario(Long usuarioId){
        return repository.findByUsuarioId(usuarioId);
    }
}