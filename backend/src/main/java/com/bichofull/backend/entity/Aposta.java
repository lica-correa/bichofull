package com.bichofull.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "apostas")
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private LocalDate data;

    private Boolean ganhou;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public Long getId() { return id; }
    public Double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public Boolean getGanhou() { return ganhou; }
    public Usuario getUsuario() { return usuario; }
    public Animal getAnimal() { return animal; }

    public void setId(Long id) { this.id = id; }
    public void setValor(Double valor) { this.valor = valor; }
    public void setData(LocalDate data) { this.data = data; }
    public void setGanhou(Boolean ganhou) { this.ganhou = ganhou; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setAnimal(Animal animal) { this.animal = animal; }
}