package com.example.EvaluationModuleJava.dao;

import com.example.EvaluationModuleJava.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseDao extends JpaRepository<Entreprise, Integer> {

}