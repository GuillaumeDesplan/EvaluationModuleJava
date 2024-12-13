package com.example.EvaluationModuleJava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Salarie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Size(min = 3, max = 100)
    @NotBlank(message = "Le matricule doit avoir au minimum 3 lettres et max 10 lettres")
    public String matricule;

    @NotBlank(message = "Le codeBarre ne peut pas être vide")
    public String codeBarre;

    @OneToOne(optional = false)
    public Convention conventions;

    @ManyToOne(optional = false)
    public Convention convention;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 3, max = 100) @NotBlank(message = "Le matricule doit avoir au minimum 3 lettres et max 10 lettres") String getMatricule() {
        return matricule;
    }

    public void setMatricule(@Size(min = 3, max = 100) @NotBlank(message = "Le matricule doit avoir au minimum 3 lettres et max 10 lettres") String matricule) {
        this.matricule = matricule;
    }

    public @NotBlank(message = "Le codeBarre ne peut pas être vide") String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(@NotBlank(message = "Le codeBarre ne peut pas être vide") String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Convention getConventions() {
        return conventions;
    }

    public void setConventions(Convention conventions) {
        this.conventions = conventions;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }
}
