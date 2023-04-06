package it.mario.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Ruoli {
    private final String prefisso = "ROLE_";
    private String user, ruolo;


    public void setRuolo(String ruolo) {
        this.ruolo = prefisso + ruolo.toUpperCase();
    }

    public Ruoli(String user, String ruolo) {
        this.user = user;
        this.ruolo = prefisso + ruolo.toUpperCase();
    }
}//
