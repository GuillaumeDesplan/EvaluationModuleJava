package com.example.EvaluationModuleJava.controller;

import com.example.EvaluationModuleJava.dao.ConventionDao;
import com.example.EvaluationModuleJava.model.Convention;
import com.example.EvaluationModuleJava.securite.IsAdmin;
import com.example.EvaluationModuleJava.securite.IsEntreprise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ConventionController {

    @Autowired
    private ConventionDao conventionDao;

    @IsEntreprise
    @GetMapping("/convention")
    public List<Convention> getAll() {
        return conventionDao.findAll();
    }

    @IsEntreprise
    @GetMapping("/convention/{id}")
    public ResponseEntity<Convention> get(@PathVariable Integer id) {
        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if (optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/convention")
    public ResponseEntity<Convention> create(@RequestBody @Valid Convention convention) {
        if (convention.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // ID ne doit pas être présent pour une création
        }

        // Validation des règles métier
        if (convention.getSubvention() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (convention.getSalaireMaximum() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        conventionDao.save(convention);
        return new ResponseEntity<>(convention, HttpStatus.CREATED);
    }


    @IsAdmin
    @PutMapping("/convention/{id}")
    public ResponseEntity<Convention> update(@RequestBody @Valid Convention conventionEnvoye, @PathVariable Integer id) {
        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if (optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Récupération de l'entité existante
        Convention conventionExistante = optionalConvention.get();

        // Mise à jour des champs uniquement si les valeurs sont fournies
        if (conventionEnvoye.getNom() != null) {
            conventionExistante.setNom(conventionEnvoye.getNom());
        }
        if (conventionEnvoye.getSubvention() != null) {
            conventionExistante.setSubvention(conventionEnvoye.getSubvention());
        }
        if (conventionEnvoye.getSalaireMaximum() > 0) {
            conventionExistante.setSalaireMaximum(conventionEnvoye.getSalaireMaximum());
        }

        conventionDao.save(conventionExistante);
        return new ResponseEntity<>(conventionExistante, HttpStatus.OK);
    }


    @IsAdmin
    @DeleteMapping("/convention/{id}")
    public ResponseEntity<Convention> delete(@PathVariable Integer id) {
        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if (optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        conventionDao.deleteById(id);
        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }
}
