package sample.bankingapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Uzivatel implements Serializable {
    private Random rnd = new Random();
    private String cisloUctu;
    private String jmeno;
    private String uzivatelsakeJmeno;
    private String heslo;
    private LocalDate datumZalozeni;
    private Mena mena;
    private int mesicniPrijem;
    private int zustatek;
    private int limitJidlo;
    private int limitDoprava;
    private int limitZabava;
    private int limitBezny;
    private int limitOstatni;

    private ArrayList<Transakce> seznamTransakci;

    public Uzivatel(String jmeno, String uzivatelsakeJmeno, String heslo) {
        this.jmeno = jmeno;
        this.uzivatelsakeJmeno = uzivatelsakeJmeno;
        this.heslo = heslo;
        this.datumZalozeni = LocalDate.now();
       this.mena = Mena.CZK;
        this.zustatek = 0;
        this.seznamTransakci = new ArrayList<>();
        this.limitJidlo = 1000;
        this.limitDoprava = 1000;
        this.limitZabava = 1000;
        this.limitBezny = 1000;
        this.limitOstatni = 1000;
        cisloUctu = generaceUctu();
    }

    public String generaceUctu() {
        StringBuilder cislo = new StringBuilder();
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

    public int getLimitOstatni() {
        return limitOstatni;
    }

    public void setLimitOstatni(int limitOstatni) {
        this.limitOstatni = limitOstatni;
    }

    public int getLimitBezny() {
        return limitBezny;
    }

    public void setLimitBezny(int limitBezny) {
        this.limitBezny = limitBezny;
    }

    public int getLimitZabava() {
        return limitZabava;
    }

    public void setLimitZabava(int limitZabava) {
        this.limitZabava = limitZabava;
    }

    public int getLimitDoprava() {return limitDoprava;}

    public void setLimitDoprava(int limitDoprava) {
        this.limitDoprava = limitDoprava;
    }

    public int getLimitJidlo() {
        return limitJidlo;
    }

    public void setLimitJidlo(int limitJidlo) {
        this.limitJidlo = limitJidlo;
    }

    public Mena getMena() {
        return mena;
    }

    public void setMena(Mena mena) {
        this.mena = mena;
    }

    public String getCisloUctu() {
        return cisloUctu;
    }
}