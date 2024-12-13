package com.example.EvaluationModuleJava.dao;

import com.example.EvaluationModuleJava.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionDao extends JpaRepository<Convention, Integer> {

}