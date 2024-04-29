package com.example.carros.Controller;

import com.example.carros.Service.CorService;
import com.example.carros.model.Cor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cores")
public class CorController {

    @Autowired
    private CorService corService;

    @GetMapping
    public ResponseEntity<List<Cor>> getAllCores() {
        List<Cor> cores = corService.getAllCores();
        return ResponseEntity.ok(cores);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cor> getCorById(@PathVariable long id) {
        Optional<Cor> cor = corService.getCorById(id);
        return cor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Cor> saveCor(@RequestBody Cor cor) {
        Cor savedCor = corService.saveCor(cor);
        return new ResponseEntity<>(savedCor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cor> updateCor(@PathVariable long id, @RequestBody Cor newCor) {
        Cor updatedCor = corService.updateCor(id, newCor);
        return ResponseEntity.ok(updatedCor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCor(@PathVariable long id) {
        corService.deleteCor(id);
        return ResponseEntity.noContent().build();
    }
}
