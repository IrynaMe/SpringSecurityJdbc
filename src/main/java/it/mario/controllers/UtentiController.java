package it.mario.controllers;

import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.service.SecurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtentiController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SecurService securService;

    @PostMapping("/inserisci/utente")
    @ResponseBody
    public void insert(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "enabled") int enabled,
                       @RequestParam(value = "ruolo") String ruolo) {

        String passwordCriptata = passwordEncoder.encode(password);
        Utente utente = new Utente(username, passwordCriptata, enabled);
        Ruoli ruoli = new Ruoli(username, ruolo);
        securService.salvaSuDb(utente, ruoli);
    }
}
