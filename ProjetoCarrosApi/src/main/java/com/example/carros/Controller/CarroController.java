package com.example.carros.Controller;

import com.example.carros.Repository.CarroRepository;
import com.example.carros.Repository.MarcaRepository;
import com.example.carros.Repository.CorRepository;
import com.example.carros.Service.CarroService;
import com.example.carros.model.Carro;
import com.example.carros.model.Cor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin("http://localhost:4200/")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros() {
        List<Carro> carros = carroRepository.findAll();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Carro carro = carroOptional.get();
        Hibernate.initialize(carro.getMarca());
        for (Cor cor : carro.getCores()) {
            Hibernate.initialize(cor);
        }
        return ResponseEntity.ok(carro);
    }


    @PostMapping
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro) {
        Carro savedCarro = carroService.saveCarro(carro);
        return new ResponseEntity<>(savedCarro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Carro> updateCarroWithMarcaAndCores(@PathVariable long id, @RequestBody Carro carro) {
        if (!carroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        for (Cor cor : carro.getCores()) {
            if (cor.getId() == 0) {
                Cor savedCor = corRepository.save(cor);
                cor.setId((int) savedCor.getId());
            }
        }
        Carro updatedCarro = carroService.updateCarro(id, carro);
        return ResponseEntity.ok(updatedCarro);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable long id) {
        carroService.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }

}
