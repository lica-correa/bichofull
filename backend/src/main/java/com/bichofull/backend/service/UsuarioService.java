package com.bichofull.backend.service;

import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // SALVAR
    public Usuario salvar(Usuario usuario) {

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new RuntimeException("Email obrigatório");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new RuntimeException("Senha obrigatória");
        }

        return repository.save(usuario);
    }

    // LISTAR
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // ATUALIZAR
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return repository.save(usuario);
    }

    // DELETAR
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        repository.delete(usuario);
    }

    // LOGIN SIMPLES
    public String login(String email, String senha) {

        Usuario usuario = buscarPorEmail(email);

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        return "login-sucesso";
    }
}