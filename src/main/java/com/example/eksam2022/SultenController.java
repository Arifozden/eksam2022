package com.example.eksam2022;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class SultenController {
    @GetMapping("/sjekk")
    public String sjekk(){
        return "OK";
    }

    @Autowired
    SultenRepository repo;

    @PostMapping("/bestilling")
    public void bestilling(Bestilling bestilling,HttpServletResponse response) throws IOException{
        if(sjekkInnlogging()){
            if(!repo.leggInnBestilling(bestilling)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Det skjedde en feil ved innlegging av bestilling.\n" +
                        "Prøv igjen om litt.");
            }
        }else {
            response.sendError(HttpStatus.FORBIDDEN.value(),"Du må først logge inn for å legge inn en ny\n" +
                    "bestilling.");
        }
    }

    @PostMapping("/kunde")
    public void kunde(Kunde kunde){
        repo.leggInnKunde(kunde);
    }

    @Autowired
    HttpSession session;

    @PostMapping("/logginn")
    public void logginn(Kunde kunde){
        if(repo.sjekkMobilogPassord(kunde)){
            session.setAttribute("innlogget",kunde);
        }
    }

    public boolean sjekkInnlogging(){
        if(session.getAttribute("innlogget") !=null){
            return true;
        }else {
            return false;
        }
    }

    @GetMapping("/loggut")
    public void loggut(){
        session.removeAttribute("innlogget");
    }


}
