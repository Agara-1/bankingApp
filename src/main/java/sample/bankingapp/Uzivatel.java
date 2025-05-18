package sample.bankingapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Uzivatel implements Serializable {
    private Random rnd = new Random();
    private int cisloGenerator;
    private StringBuilder cislo = new StringBuilder();
    private String jmeno;
    private String uzivatelsakeJmeno;
    private String heslo;
    private LocalDate datumZalozeni;

    public Uzivatel(String jmeno, String uzivatelsakeJmeno, String heslo) {
        this.jmeno = jmeno;
        this.uzivatelsakeJmeno = uzivatelsakeJmeno;
        this.heslo = heslo;
        this.datumZalozeni = LocalDate.now();
    }

    public String generaceUctu() {
        for (int i = 0; i < 12; i++) {
            cislo.append(rnd.nextInt(10));
            if ((i + 1) % 4 == 0 && i < 11) {
                cislo.append(" ");
            }
        }
        return cislo.toString();
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getUzivatelsakeJmeno() {
        return uzivatelsakeJmeno;
    }

    public void setUzivatelsakeJmeno(String uzivatelsakeJmeno) {
        this.uzivatelsakeJmeno = uzivatelsakeJmeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    @Override
    public String toString() {
        return "Uzivatel{" +
                "jmeno='" + jmeno + '\'' +
                ", uzivatelsakeJmeno='" + uzivatelsakeJmeno + '\'' +
                ", heslo='" + heslo + '\'' +
                '}';
    }

    public LocalDate getDatumZalozeni() {
        return datumZalozeni;
    }

    public void setDatumZalozeni(LocalDate datumZalozeni) {
        this.datumZalozeni = datumZalozeni;
    }

    public StringBuilder getCislo() {
        return cislo;
    }
}
