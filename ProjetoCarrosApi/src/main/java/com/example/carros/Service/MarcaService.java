package com.example.carros.Service;

import com.example.carros.Repository.MarcaRepository;
import com.example.carros.model.Marca;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marcas = repository.findAll();
        return ResponseEntity.ok(marcas);
    }

    public ResponseEntity<Marca> getMarcaById(Long id) {
        Optional<Marca> marcaOptional = repository.findById(id);
        return marcaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Marca> saveMarca(Marca marca) {
        Marca savedMarca = repository.save(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMarca);
    }

    public ResponseEntity<Marca> updateMarca(Long id, Marca marca) {
        marca.setId((long) Math.toIntExact(id));
        Marca updatedMarca = repository.save(marca);
        return ResponseEntity.ok(updatedMarca);
    }

    public ResponseEntity<Void> deleteMarca(Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
