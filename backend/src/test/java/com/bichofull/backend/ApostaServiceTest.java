package com.bichofull.backend;

import com.bichofull.backend.entity.*;
import com.bichofull.backend.service.ApostaService;
import com.bichofull.backend.service.UsuarioService;
import com.bichofull.backend.repository.AnimalRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ApostaServiceTest {

    @Autowired
    private ApostaService apostaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void deveCriarApostaComSaldo() {
        Usuario u = new Usuario();
        u.setNome("User");
        u.setEmail("test" + System.currentTimeMillis() + "@email.com");
        u.setSenha("123");
        u.setSaldo(100.0);

        u = usuarioService.salvar(u);

        Animal animal = new Animal();
        animal.setNome("Leão");
        animal.setNumero(7);

        animal = animalRepository.save(animal);

        Aposta aposta = new Aposta();
        aposta.setValor(50.0);
        aposta.setUsuario(u);
        aposta.setAnimal(animal);

        Aposta salva = apostaService.salvar(aposta);

        assertNotNull(salva.getId());
    }

    @Test
    void naoDeveApostarSemSaldo() {
        Usuario u = new Usuario();
        u.setNome("User2");
        u.setEmail("test" + System.currentTimeMillis() + "@email.com");
        u.setSenha("123");
        u.setSaldo(10.0);

        u = usuarioService.salvar(u);

        Animal animal = new Animal();
        animal.setNome("Tigre");
        animal.setNumero(8);

        animal = animalRepository.save(animal);

        Aposta aposta = new Aposta();
        aposta.setValor(50.0);
        aposta.setUsuario(u);
        aposta.setAnimal(animal);

        assertThrows(Exception.class, () -> {
            apostaService.salvar(aposta);
        });
    }
}