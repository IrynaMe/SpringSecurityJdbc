package it.mario.repository;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("interfacciaRepository")

public class InterfacciaRepositoryImpl implements InterfacciaRepository {

    @Autowired
    @Qualifier("jdbcTemplatePrimo")
    JdbcTemplate jdbcTemplatePrimo;


    @Override
    public int salvaSuDb(Utente utente, Ruoli ruoli) {
        try {
            jdbcTemplatePrimo.update("insert ignore into utenti (username,password,enabled) values (?,?,?)",
                    utente.getUsername(), "{bcrypt}" + utente.getPassword(), utente.getEnabled());
            jdbcTemplatePrimo.update("insert ignore into ruoli (user ,ruolo) values (?,?)",
                    ruoli.getUser(), ruoli.getRuolo());
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    @Override
    public int salvaImpiegato(Impiegati impiegato) {
        return 0;
    }
}//
