package com.example.carros.Controller;

import com.example.carros.Service.MarcaService;
import com.example.carros.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        return service.getAllMarcas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable Long id) {
        return service.getMarcaById(id);
    }
    @PostMapping
    public ResponseEntity<Marca> saveMarca(@RequestBody Marca marca) {
        return service.saveMarca(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
        return service.updateMarca(Long.valueOf(id), marca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
        return service.deleteMarca(Long.valueOf(id));
    }
}
