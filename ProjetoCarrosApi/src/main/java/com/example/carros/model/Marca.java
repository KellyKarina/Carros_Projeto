package com.example.carros.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "marca")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarca")
    private int id;

    @Column(name = "nomemarca")
    private String nomeMarca;
    public Marca() {
    }

    public Marca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
