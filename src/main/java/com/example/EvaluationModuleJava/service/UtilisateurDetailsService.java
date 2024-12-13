package com.example.EvaluationModuleJava.service;

import com.example.EvaluationModuleJava.dao.UtilisateurDao;
import com.example.EvaluationModuleJava.model.Utilisateur;
import com.example.EvaluationModuleJava.securite.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Appel de la méthode sur l'instance utilisateurDao
        Utilisateur utilisateur = utilisateurDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        return new AppUserDetails(utilisateur);
    }
}
