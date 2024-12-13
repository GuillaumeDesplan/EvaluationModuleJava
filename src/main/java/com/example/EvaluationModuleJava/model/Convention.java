package com.example.EvaluationModuleJava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Convention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotBlank(message = "Le nom de la convention ne peut pas être vide")
    public String nom;

    @Min(value = 0, message = "La subvention ne peut pas être négative")
    public Float subvention;

    @Min(value = 1, message = "Le nombre maximum de salariés doit être au moins égal à 1")
    public int salaireMaximum;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    public Entreprise entreprise;

    @OneToMany(mappedBy = "convention")
    public List<Salarie> salaries;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom de la convention ne peut pas être vide") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom de la convention ne peut pas être vide") String nom) {
        this.nom = nom;
    }

    public List<Salarie> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salarie> salaries) {
        this.salaries = salaries;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Min(value = 1, message = "Le nombre maximum de salariés doit être au moins égal à 1")
    public int getSalaireMaximum() {
        return salaireMaximum;
    }

    public void setSalaireMaximum(@Min(value = 1, message = "Le nombre maximum de salariés doit être au moins égal à 1") int salaireMaximum) {
        this.salaireMaximum = salaireMaximum;
    }

    public @Min(value = 0, message = "La subvention ne peut pas être négative") Float getSubvention() {
        return subvention;
    }

    public void setSubvention(@Min(value = 0, message = "La subvention ne peut pas être négative") Float subvention) {
        this.subvention = subvention;
    }
}
