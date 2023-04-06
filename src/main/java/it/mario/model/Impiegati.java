package it.mario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Impiegati {
    private String cognome;
    private int id, eta, stipendio;
}
