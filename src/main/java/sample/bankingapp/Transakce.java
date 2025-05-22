package sample.bankingapp;

import java.time.LocalDate;
import java.util.Date;

public class Transakce {
    private int id;
    private LocalDate datum;
    private int castka;
    private KategorieTransakci kategorieVydaj;
    private String kategoriePrijem;
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

    public KategorieTransakci getKategorie() {
        return kategorieVydaj;
    }

    public void setKategorie(KategorieTransakci kategorie) {
        this.kategorieVydaj = kategorie;
    }

    public TypTransakce getTypTransakce() {
        return typTransakce;
    }

    public void setTypTransakce(TypTransakce typTransakce) {
        this.typTransakce = typTransakce;
    }

    public String getKategoriePrijem() {
        return kategoriePrijem;
    }

    public void setKategoriePrijem(String kategoriePrijem) {
        this.kategoriePrijem = kategoriePrijem;
    }
}
