package com.bichofull.backend.controller;

import com.bichofull.backend.entity.Aposta;

import com.bichofull.backend.service.ApostaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apostas")
@CrossOrigin("*")
public class ApostaController {

    @Autowired
    private ApostaService service;

    @PostMapping
    public Aposta criar(@RequestBody Aposta aposta) {
        return service.salvar(aposta);
    }

    @GetMapping("/resultado/{id}")
    public String resultado(@PathVariable Long id) {
        return service.processarResultado(id);
    }

    @GetMapping
    public List<Aposta> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{id}")
    public List<Aposta> listarPorUsuario(@PathVariable Long id) {
        return service.buscarPorUsuario(id);
    }
}