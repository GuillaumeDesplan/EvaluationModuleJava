package com.example.EvaluationModuleJava.securite;

import com.example.EvaluationModuleJava.dao.UtilisateurDao;
import com.example.EvaluationModuleJava.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findByEmail(email);

        if (optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("Pseudo introuvable");
        }

        return new AppUserDetails(optionalUtilisateur.get());
    }
}
