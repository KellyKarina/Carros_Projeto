package com.example.carros.Repository;

import com.example.carros.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNomeMarcaIgnoreCase(String nomeMarca);
}
