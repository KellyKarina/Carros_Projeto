package com.example.carros.Controller;

import com.example.carros.Service.CarroService;
import com.example.carros.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin("http://localhost:4200/")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros() {
        return carroService.getAllCarros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable Integer id) {
        return carroService.getCaroById(Long.valueOf(id));
    }

    @PostMapping
    public ResponseEntity<Carro> saveCarro( @RequestBody Carro carro) {
        return carroService.saveCarro(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro(@PathVariable Integer id, @RequestBody Carro carro) {
        return carroService.updateCarro(Long.valueOf(id), carro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Integer id) {
        return carroService.deleteCarro(Long.valueOf(id));
    }
}
