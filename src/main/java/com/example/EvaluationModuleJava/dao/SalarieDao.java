package com.example.EvaluationModuleJava.dao;

import com.example.EvaluationModuleJava.model.Salarie;
import com.example.EvaluationModuleJava.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalarieDao extends JpaRepository<Salarie, Integer> {

}