package sample.bankingapp;

import java.time.LocalDate;
import java.util.Date;

public class Transakce {
    private int id;
    private LocalDate datum;
    private int castka;
    private String kategorie;
    private TypTransakce typTransakce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getCastka() {
        return castka;
    }

    public void setCastka(int castka) {
        this.castka = castka;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public TypTransakce getTypTransakce() {
        return typTransakce;
    }

    public void setTypTransakce(TypTransakce typTransakce) {
        this.typTransakce = typTransakce;
    }
}
