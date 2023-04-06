package it.mario.service;

import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.repository.InterfacciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class SecurServiceImpl implements SecurService {

    @Autowired
    @Qualifier("interfacciaRepository")
    InterfacciaRepository interfacciaRepository;

    @Override
    public int salvaSuDb(Utente utente, Ruoli ruoli) {
        return interfacciaRepository.salvaSuDb(utente, ruoli);
    }
}//
