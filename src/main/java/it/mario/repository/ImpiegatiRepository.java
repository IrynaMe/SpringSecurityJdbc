package it.mario.repository;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.service.ImpiegatiRowMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Qualifier("impiegatiRepository")
//
public class ImpiegatiRepository implements InterfacciaRepository {
    @Autowired
    @Qualifier("jdbcTemplateSecondo")
    JdbcTemplate jdbcTemplateSecondo;

    @Override
    public int salvaSuDb(Utente utente, Ruoli ruoli) {
        return 0;
    }

    @Override
    public int salvaImpiegato(Impiegati impiegato) {
        String sql = "insert ignore into impiegato (id, cognome, eta, stipendio) values (?,?,?,?)";
        int x = jdbcTemplateSecondo.update(sql,
                impiegato.getId(),
                impiegato.getCognome(),
                impiegato.getEta(),
                impiegato.getStipendio());
        return x;
    }

    public List<Impiegati> ricercaTutti() {
        String sql = "select * from impiegato";
        List<Impiegati> impiegatiLista = jdbcTemplateSecondo.query(sql, new ImpiegatiRowMap());
        return impiegatiLista;
    }
}//
