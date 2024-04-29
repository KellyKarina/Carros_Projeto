package com.example.carros.Repository;

import com.example.carros.model.Carro;
import com.example.carros.model.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
    Optional<Cor> findByNomeCor(String nomeCor);

    List<Cor> findByCarros(Carro carro);
}
