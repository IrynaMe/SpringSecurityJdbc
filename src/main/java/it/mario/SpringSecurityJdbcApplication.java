package it.mario;

import it.mario.model.Impiegati;
import it.mario.repository.ImpiegatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityJdbcApplication implements CommandLineRunner {
    @Autowired
    ImpiegatiRepository impiegatiRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //manage data of 2nd db->alternative to all endpoints
      /*  System.out.println("Inserisci un impiegato su db");
        Impiegati impiegato=new Impiegati("Rossi",1,45,1000);
        int x= impiegatiRepository.salvaImpiegato(impiegato);
        if (x==1){
            System.out.println("Salvato impiegato");
        }else{
            System.out.println("Errore salvataggio impiegato");
        }*/
    }


}//
