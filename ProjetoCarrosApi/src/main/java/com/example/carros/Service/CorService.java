package com.example.carros.Service;

import com.example.carros.Repository.CorRepository;
import com.example.carros.model.Cor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CorService {

    @Autowired
    private CorRepository corRepository;

    public List<Cor> getAllCores() {
        return corRepository.findAll();
    }

    public Optional<Cor> getCorById(Long id) {
        return corRepository.findById(id);
    }

    public Cor saveCor(Cor cor) {
        return corRepository.save(cor);
    }

    public void deleteCor(Long id) {
        corRepository.deleteById(id);
    }

    public Cor updateCor(Long id, Cor newCor) {
        return corRepository.findById(id).map(cor -> {
            cor.setNomeCor(newCor.getNomeCor());
            return corRepository.save(cor);
        }).orElseGet(() -> {
            newCor.setId(Math.toIntExact(id));
            return corRepository.save(newCor);
        });
    }

    public Optional<Cor> getCorByNome(String nome) {
        return corRepository.findByNomeCor(nome);
    }

    public Cor getOrCreateCor(String nomeCor) {
        if (nomeCor == null) {
            return null;
        }
        Optional<Cor> existingCor = getCorByNome(nomeCor);
        return existingCor.orElseGet(() -> saveCor(new Cor(nomeCor)));
    }

    public boolean existsById(Long id) {
        return corRepository.existsById(id);
    }

    public Cor saveOrUpdateCor(Cor cor) {
        Optional<Cor> existingCor = corRepository.findByNomeCor(cor.getNomeCor());
        return existingCor.orElseGet(() -> saveCor(cor));
    }
}
