package com.example.eksam2022;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SultenRepository {
    Logger logger= LoggerFactory.getLogger(SultenRepository.class);

    @Autowired
    private JdbcTemplate db;

    @Transactional
    public boolean leggInnBestilling(Bestilling bestilling,Kunde kunde){
       // String sjekkKunde="SELECT COUNT(*) FROM Kunde WHERE mobil=?";
        String hentKunde="SELECT KID FROM Kunde WHERE mobil=?";
        //String settInnKunde="INSERT INTO Kunde (navn,mobil,epost) VALUES (?,?,?)";
        String sqlBestilling="INSERT INTO Bestilling (KID,bord,varer) VALUES (?,?,?)";


        try {
            int KID=db.queryForObject(hentKunde, Integer.class,bestilling.getMobil());

                db.update(sqlBestilling,KID,bestilling.getBord(),bestilling.getVarer());


        }catch (Exception e){
            logger.error("Feil ved innlegging av bestilling: "+ e);
            return false;
        }
        return true;
    }

    public void leggInnKunde(Kunde kunde){
        String hashetPassord=BCrypt.hashpw(kunde.getPassord(),BCrypt.gensalt(14));
        String settInnKunde="INSERT INTO Kunde (navn,mobil,epost,passord) VALUES (?,?,?,?)";
        db.update(settInnKunde,kunde.getNavn(),kunde.getMobil(),kunde.getEpost(),hashetPassord);
    }

    public boolean sjekkMobilogPassord(Kunde kunde){
        String sql="SELECT * FROM Kunde WHERE mobil=?";
        Kunde dbKunde=db.queryForObject(sql,BeanPropertyRowMapper.newInstance(Kunde.class),kunde.getMobil());
        String hashetPassord=dbKunde.getPassord();
        return BCrypt.checkpw(kunde.getPassord(),hashetPassord);
    }
}
