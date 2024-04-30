package com.example.carros.Service;

import com.example.carros.Repository.CarroRepository;
import com.example.carros.Repository.CorRepository;
import com.example.carros.Repository.MarcaRepository;
import com.example.carros.model.Carro;
import com.example.carros.model.Cor;
import com.example.carros.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private CarroRepository carroRepository;

    public ResponseEntity<List<Carro>> getAllCarros() {
        List<Carro> carros = repository.findAll();
        return ResponseEntity.ok(carros);
    }

    public ResponseEntity<Carro> getCaroById(Long id) {
        Optional<Carro> carroOptional = repository.findById(id);
        return carroOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Carro> saveCarro(Carro carro) {
        Marca marca = carro.getMarca();
        Optional<Marca> marcaOptional = marcaRepository.findByNomeMarcaIgnoreCase(marca.getNomeMarca());
        if (marcaOptional.isEmpty()) {
            marca = marcaRepository.save(marca);
        } else {
            marca = marcaOptional.get();
            carro.setMarca(marca);
        }
        List<Cor> cores = carro.getCores();
        if (cores != null && !cores.isEmpty()) {
            List<Cor> coresSalvas = new ArrayList<>();
            for (Cor cor : cores) {
                Optional<Cor> corOptional = corRepository.findByNomeCorIgnoreCase(cor.getNomeCor());
                if (corOptional.isEmpty()) {
                    Cor corSalva = corRepository.save(cor);
                    coresSalvas.add(corSalva);
                } else {
                    coresSalvas.add(corOptional.get());
                }
            }
            carro.setCores(coresSalvas);
        }
        Carro savedCar = carroRepository.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    public ResponseEntity<Carro> updateCarro(Long id, Carro carro) {
        Optional<Carro> carroOptional = repository.findById(id);
        if (carroOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Carro veiculoExistente = carroOptional.get();
        Marca marca = carro.getMarca();
        if (marca.getId() == null) {
            Marca marcaSalva = marcaRepository.save(marca);
            veiculoExistente.setMarca(marcaSalva);
        } else {
            veiculoExistente.setMarca(marca);
        }
        veiculoExistente.setNomeCarro(carro.getNomeCarro());
        veiculoExistente.setAnoFabricacaoCarro(carro.getAnoFabricacaoCarro());
        veiculoExistente.setAnoModeloCarro(carro.getAnoModeloCarro());
        veiculoExistente.setModeloCarro(carro.getModeloCarro());
        Carro veiculoSalvo = repository.save(veiculoExistente);
        return ResponseEntity.ok(veiculoSalvo);
    }


    public ResponseEntity<Void> deleteCarro(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

































//package com.example.carros.Service;
//
//import com.example.carros.Repository.CarroRepository;
//import com.example.carros.Repository.CorRepository;
//import com.example.carros.Repository.MarcaRepository;
//import com.example.carros.model.Carro;
//import com.example.carros.model.Cor;
//import com.example.carros.model.Marca;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//@Transactional
//public class CarroService {
//
//    @Autowired
//    private CarroRepository repository;
//
//    @Autowired
//    private MarcaRepository marcaRepository;
//
//    @Autowired
//    private CorRepository corRepository;
//
//    public ResponseEntity<List<Carro>> getAllCars() {
//        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
//    }
//
//    public ResponseEntity<Carro> getCarById(Long id) {
//        Optional<Carro> carroOptional = repository.findById(id);
//        return carroOptional.map(carro -> ResponseEntity.ok().body(carro))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    public ResponseEntity<Carro> addCar(Carro carro) {
//        Marca marca = marcaRepository.findByNome(carro.getMarca().getNome());
//        if (marca == null) {
//            marca = new Marca();
//            marca.setNome(carro.getMarca().getNome());
//            marca = marcaRepository.save(marca);
//        }
//
//        List<Cor> coresSalvas = new ArrayList<>();
//        for (Cor cor : carro.getCores()) {
//            Cor corExistente = corRepository.findByCor(cor.getCor());
//            if (corExistente == null) {
//                corExistente = new Cor();
//                corExistente.setCor(cor.getCor());
//                corExistente = corRepository.save(corExistente);
//            }
//            coresSalvas.add(corExistente);
//        }
//
//        carro.setMarca(marca);
//        carro.setCores(coresSalvas);
//        return new ResponseEntity<>(repository.save(carro), HttpStatus.CREATED);
//    }
//
//    public ResponseEntity<Carro> editCar(Long id, Carro carro) {
//        Carro carroExistente = repository.findById(id).orElse(null);
//        if (carroExistente == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Marca marca = marcaRepository.findByNome(carro.getMarca().getNome());
//        if (marca == null) {
//            marca = new Marca();
//            marca.setNome(carro.getMarca().getNome());
//            marca = marcaRepository.save(marca);
//        }
//
//        List<Cor> coresSalvas = new ArrayList<>();
//        for (Cor cor : carro.getCores()) {
//            Cor corExistente = corRepository.findByCor(cor.getCor());
//            if (corExistente == null) {
//                corExistente = new Cor();
//                corExistente.setCor(cor.getCor());
//                corExistente = corRepository.save(corExistente);
//            }
//            coresSalvas.add(corExistente);
//        }
//
//        carroExistente.setNomeCarro(carro.getNomeCarro());
//        carroExistente.setAnoFabricacaoCarro(carro.getAnoFabricacaoCarro());
//        carroExistente.setAnoModeloCarro(carro.getAnoModeloCarro());
//        carroExistente.setModeloCarro(carro.getModeloCarro());
//
//        carroExistente.setMarca(marca);
//        carroExistente.setCores(coresSalvas);
//
//        return new ResponseEntity<>(repository.save(carroExistente), HttpStatus.OK);
//    }
//
//    public ResponseEntity<Optional<Carro>> deleteCar(Long id) {
//        if (repository.existsById(id)) {
//            repository.deleteById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
