package it.mario.service;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.repository.ImpiegatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpiegatiService {
    @Autowired
    @Qualifier("impiegatiRepository")
    ImpiegatiRepository impiegatiRepository;

    public List<Impiegati> estraiTuttiImpiegati() {
        return impiegatiRepository.ricercaTutti();
    }
    // controller ->calls->service->calls->repository


    public int salvaDbImp(Impiegati impiegato) {
        return impiegatiRepository.salvaImpiegato(impiegato);
    }
}
