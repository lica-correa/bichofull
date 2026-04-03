package com.bichofull.backend.service;

import com.bichofull.backend.entity.Aposta;
import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.entity.Animal;

import com.bichofull.backend.repository.ApostaRepository;
import com.bichofull.backend.repository.UsuarioRepository;
import com.bichofull.backend.repository.AnimalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ApostaService {

    @Autowired
    private ApostaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    // CRIAR APOSTA
    public Aposta salvar(Aposta aposta){

        Usuario usuario = usuarioRepository.findById(aposta.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Animal animal = animalRepository.findById(aposta.getAnimal().getId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        if (aposta.getValor() <= 0){
            throw new RuntimeException("Valor da aposta inválido");
        }

        // 🔥 CORREÇÃO IMPORTANTE PRA PASSAR NO TESTE
        if (usuario.getSaldo() == null || usuario.getSaldo() < aposta.getValor()){
            throw new RuntimeException("Saldo insuficiente");
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

    public int gerarNumeroSorteado() {
        return new Random().nextInt(10000);
    }

    public String processarResultado(Long apostaId) {

        Aposta aposta = repository.findById(apostaId)
                .orElseThrow(() -> new RuntimeException("Aposta não encontrada"));

        int numeroSorteado = new Random().nextInt(10000);

        int dezena = numeroSorteado % 100;
        if (dezena == 0) dezena = 100;

        int grupoSorteado = ((dezena - 1) / 4) + 1;

        int numeroAnimal = aposta.getAnimal().getNumero();

        Usuario usuario = aposta.getUsuario();

        if (numeroAnimal == grupoSorteado) {

            double premio = aposta.getValor() * 18;

            usuario.setSaldo(usuario.getSaldo() + premio);
            usuarioRepository.save(usuario);

            aposta.setGanhou(true);
            repository.save(aposta);

            return "Ganhou!\nSeu animal: " + aposta.getAnimal().getNome() +
                    "\nGrupo: " + numeroAnimal +
                    "\n\nGrupo sorteado: " + grupoSorteado +
                    "\nNúmero: " + numeroSorteado;

        } else {

            aposta.setGanhou(false);
            repository.save(aposta);

            return "Perdeu!\nSeu animal: " + aposta.getAnimal().getNome() +
                    "\nGrupo: " + numeroAnimal +
                    "\n\nGrupo sorteado: " + grupoSorteado +
                    "\nNúmero: " + numeroSorteado;
        }
    }
}