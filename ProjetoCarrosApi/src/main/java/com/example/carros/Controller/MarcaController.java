package com.example.carros.Controller;

import com.example.carros.Service.MarcaService;
import com.example.carros.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> getAllMarcas() {
        List<Marca> marcas = marcaService.getAllMarcas();
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getMarcaById(@PathVariable long id) {
        Optional<Marca> marca = marcaService.getMarcaById(id);
        return marca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> saveMarca(@RequestBody Marca marca) {
        Marca savedMarca = marcaService.getOrCreateMarca(marca.getNomeMarca());
        return new ResponseEntity<>(savedMarca, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> updateMarca(@PathVariable long id, @RequestBody Marca marca) {
        Marca updatedMarca = marcaService.updateMarca(id, marca);
        return new ResponseEntity<>(updatedMarca, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable long id) {
        marcaService.deleteMarca(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
