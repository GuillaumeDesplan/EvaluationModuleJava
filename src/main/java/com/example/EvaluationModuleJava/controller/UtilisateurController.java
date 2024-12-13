package com.example.EvaluationModuleJava.controller;

import com.example.EvaluationModuleJava.dao.UtilisateurDao;
import com.example.EvaluationModuleJava.dao.EntrepriseDao;
import com.example.EvaluationModuleJava.model.Entreprise;
import com.example.EvaluationModuleJava.model.Utilisateur;
import com.example.EvaluationModuleJava.securite.IsAdmin;
import com.example.EvaluationModuleJava.securite.IsEntreprise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private EntrepriseDao entrepriseDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @IsEntreprise
    @GetMapping("/utilisateur")
    public List<Utilisateur> getAll() {
        return utilisateurDao.findAll();
    }

    @IsEntreprise
    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> get(@PathVariable Integer id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/utilisateur")
    public ResponseEntity<Utilisateur> create(@RequestBody @Valid Utilisateur utilisateur, @RequestParam(required = false) Integer entrepriseId) {
        utilisateur.setId(null);
        utilisateur.setPassword(encoder.encode(utilisateur.getPassword()));

        if (entrepriseId != null) {
            Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(entrepriseId);
            if (optionalEntreprise.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            utilisateur.setEntreprise(optionalEntreprise.get());
        }

        utilisateurDao.save(utilisateur);
        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> update(@RequestBody @Valid Utilisateur utilisateurEnvoye, @PathVariable Integer id) {
        utilisateurEnvoye.setId(id);

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.save(utilisateurEnvoye);
        return new ResponseEntity<>(utilisateurEnvoye, HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);

        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.deleteById(id);
        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }
}
