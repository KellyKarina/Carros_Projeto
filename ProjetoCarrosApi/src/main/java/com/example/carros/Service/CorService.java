package com.example.carros.Service;

import com.example.carros.Repository.CorRepository;
import com.example.carros.model.Cor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CorService {

    @Autowired
    private CorRepository repository;

    public ResponseEntity<List<Cor>> getAllCores() {
        List<Cor> cores = repository.findAll();
        return ResponseEntity.ok(cores);
    }

    public ResponseEntity<Cor> getCorById(Long id) {
        Optional<Cor> corOptional = repository.findById(id);
        return corOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cor> saveCor(Cor cor) {
        Cor savedCor = repository.save(cor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCor);
    }

    public ResponseEntity<Cor> updateCor(Long id, Cor cor) {
        if (repository.existsById(id)) {
            cor.setId((long) Math.toIntExact(id));
            Cor updatedCor = repository.save(cor);
            return ResponseEntity.ok(updatedCor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteCor(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
