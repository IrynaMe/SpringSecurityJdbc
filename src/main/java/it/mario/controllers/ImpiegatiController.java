package it.mario.controllers;

import it.mario.model.Impiegati;
import it.mario.model.Ruoli;
import it.mario.model.Utente;
import it.mario.service.ImpiegatiService;
import it.mario.service.SecurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImpiegatiController {
    //manage endpoint to show all impiegati
    @Autowired
    ImpiegatiService impiegatiService;
    @Autowired
    SecurService securService;

    @GetMapping("/estraidati")
    @ResponseBody
    public List<Impiegati> estraiTutto() {
        List<Impiegati> impiegatiList = new ArrayList<>();
        impiegatiList = impiegatiService.estraiTuttiImpiegati();
        return impiegatiList;//flow of byte that requires RestController
    }

    @GetMapping("/dipendenti")
    public ModelAndView dipendenti(Model model) {
        ModelAndView mav = new ModelAndView("mostradipendenti");
        List<Impiegati> listaImpiegati = new ArrayList<>();
        listaImpiegati = impiegatiService.estraiTuttiImpiegati();
        mav.addObject("listaImpiegati", listaImpiegati);
        return mav;
    }

    @PostMapping("/inserisci/impiegato")
    @ResponseBody
    public void insert(@RequestParam(value = "id") int id,
                       @RequestParam(value = "cognome") String cognome,
                       @RequestParam(value = "eta") int eta,
                       @RequestParam(value = "stipendio") int stipendio) {
        Impiegati impiegato = new Impiegati(cognome, id, eta, stipendio);
        impiegatiService.salvaDbImp(impiegato);
    }

}//
