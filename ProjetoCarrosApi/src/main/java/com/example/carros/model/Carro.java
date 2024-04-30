package com.example.carros.model;


import jakarta.persistence.*;
import lombok.Data;


import java.util.List;


@Entity
@Data
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_carro")
    private String nomeCarro;

    @Column(name = "ano_fabricacao_carro")
    private Integer anoFabricacaoCarro;

    @Column(name = "ano_modelo_carro")
    private Integer anoModeloCarro;

    @Column(name = "modelo_carro")
    private String modeloCarro;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "carro_cor",
            joinColumns = @JoinColumn(name = "carro_id"),
            inverseJoinColumns = @JoinColumn(name = "cor_id")
    )
    private List<Cor> cores;
}

