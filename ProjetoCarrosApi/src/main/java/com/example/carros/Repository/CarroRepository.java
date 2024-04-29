package com.example.carros.Repository;

import com.example.carros.model.Carro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    @EntityGraph(attributePaths = {"marca", "cores"})
    List<Carro> findAll();;
}

