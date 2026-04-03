package com.bichofull.backend.controller;

import com.bichofull.backend.entity.Usuario;
import com.bichofull.backend.service.UsuarioService;
import com.bichofull.backend.dto.LoginDTO;
import com.bichofull.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
public Usuario buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id);
}

@PutMapping("/{id}")
public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
    return service.atualizar(id, usuario);
}

@DeleteMapping("/{id}")
public void deletar(@PathVariable Long id) {
    service.deletar(id);
}

@PostMapping("/login")
public String login(@RequestBody LoginDTO login){
    return service.login(login.getEmail(), login.getSenha());
}

@GetMapping("/me")
public Usuario getUsuarioLogado(@RequestHeader("Authorization") String token) {
    String email = jwtUtil.extrairEmail(token.replace("Bearer ", ""));
    return service.buscarPorEmail(email);
}

@GetMapping("/email/{email}")
public Usuario buscarPorEmail(@PathVariable String email) {
    return service.buscarPorEmail(email);
}
}