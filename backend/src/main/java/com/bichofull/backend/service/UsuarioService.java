package com.bichofull.backend.service;

import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.repository.UsuarioRepository;
import com.bichofull.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String senha){
        Usuario usuario = repository.findByEmail(email).orElse(null);

        if (usuario != null && usuario.getSenha() != null && usuario.getSenha().equals(senha)){
            return jwtUtil.gerarToken(email);
        }

        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "Email ou senha inválidos"
        );
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getSaldo() == null) {
            usuario.setSaldo(100.0);
        }
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
    return repository.findById(id).orElse(null);
}

public Usuario atualizar(Long id, Usuario usuario) {
    Usuario existente = repository.findById(id).orElse(null);
    if (existente != null) {
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        existente.setSenha(usuario.getSenha());
        existente.setSaldo(usuario.getSaldo());
        return repository.save(existente);
    }
    return null;
}

public void deletar(Long id) {
    repository.deleteById(id);
}

public Usuario buscarPorEmail(String email) {
    return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
}
}