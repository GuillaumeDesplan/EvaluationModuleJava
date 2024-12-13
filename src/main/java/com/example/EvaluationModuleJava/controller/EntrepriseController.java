package com.example.EvaluationModuleJava.controller;

import com.example.EvaluationModuleJava.dao.EntrepriseDao;
import com.example.EvaluationModuleJava.model.Entreprise;
import com.example.EvaluationModuleJava.securite.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class EntrepriseController {

    @Autowired
    private EntrepriseDao entrepriseDao;

    @IsAdmin
    @GetMapping("/entreprise")
    public List<Entreprise> getAll() {
        return entrepriseDao.findAll();
    }

    @IsAdmin
    @GetMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> get(@PathVariable Integer id) {
        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);

        if (optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/entreprise")
    public ResponseEntity<Entreprise> create(@RequestBody @Valid Entreprise entreprise) {
        entreprise.setId(null); // Forcer l'ID à null
        entrepriseDao.save(entreprise);
        return new ResponseEntity<>(entreprise, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> update(@RequestBody @Valid Entreprise entrepriseEnvoye, @PathVariable Integer id) {
        entrepriseEnvoye.setId(id);

        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);

        if (optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        entrepriseDao.save(entrepriseEnvoye);
        return new ResponseEntity<>(entrepriseEnvoye, HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> delete(@PathVariable Integer id) {
        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);

        if (optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        entrepriseDao.deleteById(id);
        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }
}
