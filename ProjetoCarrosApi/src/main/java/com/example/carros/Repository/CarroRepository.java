package com.example.carros.Repository;

import com.example.carros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}

