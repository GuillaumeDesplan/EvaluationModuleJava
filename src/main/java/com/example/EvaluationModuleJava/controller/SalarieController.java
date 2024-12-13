package com.example.EvaluationModuleJava.controller;

import com.example.EvaluationModuleJava.dao.SalarieDao;
import com.example.EvaluationModuleJava.dao.ConventionDao;
import com.example.EvaluationModuleJava.model.Salarie;
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
public class SalarieController {

    @Autowired
    private SalarieDao salarieDao;

    @Autowired
    private ConventionDao conventionDao;

    @IsEntreprise
    @GetMapping("/salarie")
    public List<Salarie> getAll() {
        return salarieDao.findAll();
    }

    @IsEntreprise
    @GetMapping("/salarie/{id}")
    public ResponseEntity<Salarie> get(@PathVariable Integer id) {
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        if (optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalSalarie.get(), HttpStatus.OK);
    }

    @IsAdmin
    @PostMapping("/salarie")
    public ResponseEntity<Salarie> create(@RequestBody @Valid Salarie salarie, @RequestParam Integer conventionId) {
        Optional<Convention> optionalConvention = conventionDao.findById(conventionId);

        if (optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Convention convention = optionalConvention.get();

        // Validation du nombre maximum de salariés
        if (convention.getSalaries().size() >= convention.getSalaireMaximum()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        salarie.setId(null); // Forcer l'ID à null
        salarie.setConvention(convention);
        salarieDao.save(salarie);

        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/salarie/{id}")
    public ResponseEntity<Salarie> update(@RequestBody @Valid Salarie salarieEnvoye, @PathVariable Integer id) {
        salarieEnvoye.setId(id);

        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        if (optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salarieDao.save(salarieEnvoye);
        return new ResponseEntity<>(salarieEnvoye, HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/salarie/{id}")
    public ResponseEntity<Salarie> delete(@PathVariable Integer id) {
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        if (optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salarieDao.deleteById(id);
        return new ResponseEntity<>(optionalSalarie.get(), HttpStatus.OK);
    }
}
