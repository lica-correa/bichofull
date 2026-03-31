package com.bichofull.backend.service;

import com.bichofull.backend.entity.Animal;
import com.bichofull.backend.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public Animal salvar(Animal animal) {
        return repository.save(animal);
    }

    public List<Animal> listar() {
        return repository.findAll();
    }
}