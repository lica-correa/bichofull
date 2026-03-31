package com.bichofull.backend.controller;

import com.bichofull.backend.entity.Animal;
import com.bichofull.backend.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@CrossOrigin("*")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @PostMapping
    public Animal criar(@RequestBody Animal animal) {
        return service.salvar(animal);
    }

    @GetMapping
    public List<Animal> listar() {
        return service.listar();
    }
}