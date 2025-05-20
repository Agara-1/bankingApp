package sample.bankingapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Uzivatel implements Serializable {
    private Random rnd = new Random();
    private int cisloGenerator;
    private StringBuilder cislo = new StringBuilder();
    private String jmeno;
    private String uzivatelsakeJmeno;
    private String heslo;
    private LocalDate datumZalozeni;
    private int mesicniPrijem;
    private int zustatek;
    private int limit;
    private ArrayList<Transakce> seznamTransakci;


    public Uzivatel(String jmeno, String uzivatelsakeJmeno, String heslo) {
        this.jmeno = jmeno;
        this.uzivatelsakeJmeno = uzivatelsakeJmeno;
        this.heslo = heslo;
        this.datumZalozeni = LocalDate.now();
        this.zustatek = 0;
        this.seznamTransakci = new ArrayList<>();
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

    public void pridaniTransakce(Transakce t) {
        seznamTransakci.add(t);
    }


    public ArrayList<Transakce> getSeznamTransakci() {
        return seznamTransakci;
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

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public int getCisloGenerator() {
        return cisloGenerator;
    }

    public void setCisloGenerator(int cisloGenerator) {
        this.cisloGenerator = cisloGenerator;
    }

    public void setCislo(StringBuilder cislo) {
        this.cislo = cislo;
    }

    public int getMesicniPrijem() {
        return mesicniPrijem;
    }

    public void setMesicniPrijem(int mesicniPrijem) {
        this.mesicniPrijem = mesicniPrijem;
    }

    public int getZustatek() {
        return zustatek;
    }

    public void setZustatek(int zustatek) {
        this.zustatek = zustatek;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
