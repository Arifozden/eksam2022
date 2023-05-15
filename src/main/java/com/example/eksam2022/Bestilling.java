package com.example.eksam2022;

public class Bestilling {
    private int KID;
    private String navn;
    private String mobil;
    private String epost;
    private String bord;
    private String varer;

    public Bestilling(int KID, String navn, String mobil, String epost, String bord, String varer) {
        this.KID = KID;
        this.navn = navn;
        this.mobil = mobil;
        this.epost = epost;
        this.bord = bord;
        this.varer = varer;
    }

    public Bestilling(){}

    public int getKID() {
        return KID;
    }

    public void setKID(int KID) {
        this.KID = KID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getBord() {
        return bord;
    }

    public void setBord(String bord) {
        this.bord = bord;
    }

    public String getVarer() {
        return varer;
    }

    public void setVarer(String varer) {
        this.varer = varer;
    }
}
