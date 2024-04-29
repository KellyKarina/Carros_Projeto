package com.example.carros.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "carro")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarro")
    private long id;

    @Column(name = "nomecarro")
    private String nomeCarro;

    @Column(name = "anofabricacaocarro")
    private Integer anoFabricacaoCarro;

    @Column(name = "anomodelocarro")
    private Integer anoModeloCarro;

    @Column(name = "modelocarro")
    private String modeloCarro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_idmarca")
    private Marca marca;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "carro_cor",
            joinColumns = {@JoinColumn(name = "carro_idcarro")},
            inverseJoinColumns = {@JoinColumn(name = "cor_idcor")}
    )
    private List<Cor> cores;


    public List<Cor> getCores() {
        return cores;
    }

    public void setCores(List<Cor> cores) {
        this.cores = cores;
    }

}
