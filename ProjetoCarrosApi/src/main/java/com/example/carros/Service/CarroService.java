package com.example.carros.Service;

import com.example.carros.Repository.CarroRepository;
import com.example.carros.model.Carro;
import com.example.carros.model.Cor;
import com.example.carros.model.Marca;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CorService corService;

    @Autowired
    private MarcaService marcaService;

    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> getCarroById(long id) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carro.getCores().size();
                    return carro;
                });
    }

    public Carro saveCarro(Carro carro) {
        Marca marca = carro.getMarca();
        if (marca != null) {
            Optional<Marca> existingMarca = marcaService.getMarcaById(marca.getId());
            if (existingMarca.isPresent()) {
                carro.setMarca(existingMarca.get());
            } else {
                Marca savedMarca = marcaService.saveMarca(marca);
                carro.setMarca(savedMarca);
            }
        }
        List<Cor> coresSalvas = new ArrayList<>();
        if (carro.getCores() != null) {
            for (Cor cor : carro.getCores()) {
                Optional<Cor> existingCor = corService.getCorByNome(cor.getNomeCor());
                if (existingCor.isPresent()) {
                    coresSalvas.add(existingCor.get());
                } else {
                    Cor savedCor = corService.saveCor(cor);
                    coresSalvas.add(savedCor);
                }
            }
        }
        carro.setCores(coresSalvas);
        return carroRepository.save(carro);
    }


    public Carro updateCarro(long id, Carro newCarro) {
        return carroRepository.findById(id).map(carro -> {
            carro.setNomeCarro(newCarro.getNomeCarro());
            carro.setAnoFabricacaoCarro(newCarro.getAnoFabricacaoCarro());
            carro.setAnoModeloCarro(newCarro.getAnoModeloCarro());
            carro.setModeloCarro(newCarro.getModeloCarro());
            Marca newMarca = newCarro.getMarca();
            if (newMarca != null) {
                Optional<Marca> existingMarca = marcaService.getMarcaById(newMarca.getId());
                if (existingMarca.isPresent()) {
                    carro.setMarca(existingMarca.get());
                } else {
                    Marca savedMarca = marcaService.saveMarca(newMarca);
                    carro.setMarca(savedMarca);
                }
            }
            carro.setCores(newCarro.getCores());
            return carroRepository.save(carro);
        }).orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com o ID: " + id));
    }

        public void deleteCarro(long id) {
        carroRepository.deleteById(id);
    }
}