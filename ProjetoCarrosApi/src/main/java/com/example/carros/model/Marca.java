package com.example.carros.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_marca")
    private String nomeMarca;

    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    private List<Carro> carros;

    public Marca() {
    }
    public String getNomeMarca() {
        return nomeMarca;
    }

}
