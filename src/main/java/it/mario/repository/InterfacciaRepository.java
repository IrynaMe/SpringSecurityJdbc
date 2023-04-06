package it.mario.repository;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import org.springframework.context.annotation.Primary;

public interface InterfacciaRepository {
    int salvaSuDb(Utente utente, Ruoli ruoli);

    int salvaImpiegato(Impiegati impiegato);

}
