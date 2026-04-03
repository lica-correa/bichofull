package com.bichofull.backend;

import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.repository.UsuarioRepository;
import com.bichofull.backend.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @Test
    void deveCriarUsuario() {
        Usuario u = new Usuario();
        u.setNome("Teste");
        u.setEmail("teste" + System.currentTimeMillis() + "@email.com"); // único
        u.setSenha("123");

        Usuario salvo = service.salvar(u);

        assertNotNull(salvo.getId());
    }

    @Test
    void deveFazerLoginValido() {
        Usuario u = new Usuario();
        u.setNome("Login");
        u.setEmail("login@email.com"); // FIXO (IMPORTANTE)
        u.setSenha("123");

        service.salvar(u);

        String token = service.login("login@email.com", "123");

        assertNotNull(token);
    }

    @Test
    void naoDeveLogarComSenhaErrada() {
        Usuario u = new Usuario();
        u.setNome("Erro");
        u.setEmail("erro@email.com"); // FIXO
        u.setSenha("123");

        service.salvar(u);

        assertThrows(RuntimeException.class, () -> {
            service.login("erro@email.com", "999");
        });
    }
}