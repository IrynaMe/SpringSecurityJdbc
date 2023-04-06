package it.mario.service;

import it.mario.model.Ruoli;
import it.mario.model.Utente;

public interface SecurService {
    int salvaSuDb(Utente utente, Ruoli ruoli);
}
