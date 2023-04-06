package it.mario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class AppGeneralController {


    @GetMapping("/benvenuto")
    @ResponseBody // to visualize return string text instead of page.html
    public String welcome() {

        return "Benvenuto sul sito";
    }

    @GetMapping("/ospiti")
    @ResponseBody
    public String ospiti() {
        return "Per gli ospiti ci sono sconti";
    }

    @GetMapping("/impiegati")
    @ResponseBody
    public String impiegati() {
        return "Orario di servizio di oggi: 8.00-18.00";
    }


    @GetMapping("/oraedata")
    public ModelAndView oraedata(Model model) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String text = "La data e orario sono:";
        ModelAndView mav = new ModelAndView("dataora");
        mav.addObject("testo", text);
        mav.addObject(localDate);
        mav.addObject(localTime);
        return mav;
    }

    @GetMapping("/orario")
    public String orario(Model model) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String text = "La data e orario sono:";
        model.addAttribute("testo", text);
        model.addAttribute(localDate);
        model.addAttribute(localTime);
        return "mostraorario";
    }


}//
