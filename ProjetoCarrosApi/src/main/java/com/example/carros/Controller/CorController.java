package com.example.carros.Controller;

import com.example.carros.Service.CorService;
import com.example.carros.model.Cor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cores")
public class CorController {

    @Autowired
    private CorService corService;

    @GetMapping
    public ResponseEntity<List<Cor>> getAllCores() {
        return corService.getAllCores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cor> getCorById(@PathVariable Long id) {
        return corService.getCorById(id);
    }

    @PostMapping
    public ResponseEntity<Cor> saveCor(@RequestBody Cor cor) {
        return corService.saveCor(cor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cor> updateCor(@PathVariable Integer id, @RequestBody Cor cor) {
        return corService.updateCor(Long.valueOf(id), cor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCor(@PathVariable Integer id) {
        return corService.deleteCor(Long.valueOf(id));
    }
}
