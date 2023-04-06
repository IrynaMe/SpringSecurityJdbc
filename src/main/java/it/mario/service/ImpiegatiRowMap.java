package it.mario.service;

import it.mario.model.Impiegati;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpiegatiRowMap implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Impiegati impiegati = new Impiegati();
        impiegati.setId(rs.getInt("id"));
        impiegati.setCognome(rs.getString("cognome"));
        impiegati.setEta(rs.getInt("eta"));
        impiegati.setStipendio(rs.getInt("stipendio"));
        return impiegati;
    }

}//
