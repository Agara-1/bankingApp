package Models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Třída reprezentuje jednu finanční transakci uživatele,
 * ať už se jedná o příjem nebo výdaj.
 * Každá transakce obsahuje datum, částku, typ (příjem/výdaj) a příslušnou kategorii.
 * Kategorie se liší podle typu transakce – pro příjmy je to řetězec (např. "výplata"),
 * pro výdaje je to hodnota z výčtu  KategorieTransakci.
 */
public class Transakce implements Serializable {

    private LocalDate datum;
    private int castka;
    private KategorieTransakci kategorieVydaj;
    private String kategoriePrijem;
    private TypTransakce typTransakce;

    public Transakce( LocalDate datum, int castka, String kategoriePrijem, TypTransakce typTransakce) {
        this.datum = datum;
        this.castka = castka;
        this.kategoriePrijem = kategoriePrijem;
        this.typTransakce = typTransakce;
    }
    public Transakce( LocalDate datum, int castka, KategorieTransakci kategorieVydaj, TypTransakce typTransakce) {
        this.datum = datum;
        this.castka = castka;
        this.kategorieVydaj = kategorieVydaj;
        this.typTransakce = typTransakce;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getCastka() {
        return castka;
    }

    public KategorieTransakci getKategorieVydaj() {
        return kategorieVydaj;
    }

    public TypTransakce getTypTransakce() {return typTransakce;}

    public String getKategoriePrijem() {
        return kategoriePrijem;
    }

}
