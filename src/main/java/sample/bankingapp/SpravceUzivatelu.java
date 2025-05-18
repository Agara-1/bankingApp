package sample.bankingapp;

import java.io.*;
import java.util.ArrayList;

public class SpravceUzivatelu {
    private ArrayList<Uzivatel> seznamUzivatelu;
    private File soubor = new File("uzivatele.dat");


    public SpravceUzivatelu() {
        this.seznamUzivatelu = nacitaniUzivatelu();

    }

    public ArrayList<Uzivatel> nacitaniUzivatelu() {
        if (soubor.length() == 0) {
            return new ArrayList<>();
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(soubor));
            seznamUzivatelu = (ArrayList<Uzivatel>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return seznamUzivatelu;
    }

    public void serializaceUzivatelu() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(soubor));
            oos.writeObject(seznamUzivatelu);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean uzivatelExistuje(Uzivatel uz) {
        for (Uzivatel u : seznamUzivatelu) {
            if (uz.getUzivatelsakeJmeno().equals(u.getUzivatelsakeJmeno())) {
                return true;
            }
        }
        return false;

    }

    public ArrayList<Uzivatel> getSeznamUzivatelu() {
        return seznamUzivatelu;
    }
}
