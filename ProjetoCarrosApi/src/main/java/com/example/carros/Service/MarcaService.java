package com.example.carros.Service;

import com.example.carros.Repository.MarcaRepository;
import com.example.carros.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> getMarcaById(long id) {
        return marcaRepository.findById(id);
    }

    public Marca saveMarca(Marca marca) {
        Optional<Marca> existingMarca = marcaRepository.findByNomeMarca(marca.getNomeMarca());
        if (existingMarca.isPresent()) {
            return existingMarca.get();
        } else {
            return marcaRepository.save(marca);
        }
    }

    public void deleteMarca(long id) {
        marcaRepository.deleteById(id);
    }

    public Marca updateMarca(long id, Marca newMarca) {
        return marcaRepository.findById(id).map(marca -> {
            marca.setNomeMarca(newMarca.getNomeMarca());
            return marcaRepository.save(marca);
        }).orElseGet(() -> {
            newMarca.setId((int) id);
            return marcaRepository.save(newMarca);
        });
    }

    public Marca getOrCreateMarca(String nomeMarca) {
        if (nomeMarca == null) {
            return null;
        }
        Optional<Marca> existingMarca = marcaRepository.findByNomeMarca(nomeMarca);
        return existingMarca.orElseGet(() -> saveMarca(new Marca(nomeMarca)));
    }

    public Optional<Marca> getMarcaByNome(String nome) {
        return marcaRepository.findByNomeMarca(nome);
    }
}
