package Models;

import sample.bankingapp.Uzivatel;

import java.io.*;
import java.util.ArrayList;
/**
 * Třída zajišťuje správu všech uživatelů v aplikaci.
 * Umožňuje načítání a ukládání uživatelských dat pomocí serializace, ověřování uživatelů
 * a práci s aktuálně přihlášeným uživatelem.
 */
@SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
public class SpravceUzivatelu {
    /** Seznam všech uživatelů.*/
    private ArrayList<Uzivatel> seznamUzivatelu;
    private Uzivatel aktualniUzivatel;
    /** Soubor, do kterého se ukládají serializovaní uživatelé. */
    private File soubor = new File("uzivatele.dat");


    public SpravceUzivatelu() {
        this.seznamUzivatelu = nacitaniUzivatelu();

    }
    /**
     * Načte seznam uživatelů ze souboru.
     *
     * @return seznam uživatelů nebo prázdný seznam, pokud je soubor prázdný nebo došlo k chybě.
     */
    public ArrayList<Uzivatel> nacitaniUzivatelu() {
        if (!soubor.exists() ||soubor.length() == 0) {
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
    /**
     * Uloží aktuální seznam uživatelů do souboru pomocí serializace.
     */
    public void serializaceUzivatelu() {

        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(soubor))){

            oos.writeObject(seznamUzivatelu);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Ověří, zda daný uživatel již existuje podle přihlašovacího jména.
     *
     * @param uz uživatel k ověření
     * @return true, pokud uživatel existuje, jinak {@code false}
     */
    public boolean uzivatelExistuje(Uzivatel uz) {
        for (Uzivatel u : seznamUzivatelu) {
            if (uz.getUzivatelsakeJmeno().equals(u.getUzivatelsakeJmeno())) {
                return true;
            }
        }
        return false;

    }
    /**
     * Vrací seznam všech uživatelů. Pokud je seznam prázdný, inicializuje jej.

     * @return seznam uživatelů
     */
    public ArrayList<Uzivatel> getSeznamUzivatelu() {
        if(seznamUzivatelu == null) {
            seznamUzivatelu = new ArrayList<>();
        }
        return seznamUzivatelu;
    }

    public Uzivatel getAktualniUzivatel() {
        return aktualniUzivatel;
    }
}
