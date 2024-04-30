package com.example.carros.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
@Table(name = "cor")
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cor")
    private String nomeCor;

    @ManyToMany(mappedBy = "cores")
    @JsonBackReference
    private List<Carro> carros;

    public Cor() {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCor() {
        return null;
    }

}
