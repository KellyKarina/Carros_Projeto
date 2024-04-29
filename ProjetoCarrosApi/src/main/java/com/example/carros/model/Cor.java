package com.example.carros.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cor")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcor")
    private int id;

    @Column(name = "nomecor")
    private String nomeCor;

    @JsonIgnore
    @ManyToMany(mappedBy = "cores", fetch = FetchType.LAZY)
    private List<Carro> carros = new ArrayList<>();

    public Cor() {

    }

    public Cor(String nomeCor) {
        this.nomeCor = nomeCor;
    }
}
