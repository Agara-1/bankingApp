package Testovani;


import Controllers.NastaveniController;
import Models.SpravceUzivatelu;
import Models.Transakce;
import Models.TypTransakce;
import sample.bankingapp.Uzivatel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class NastaveniTest {

    private NastaveniController controller;
    private Uzivatel uzivatel;

    @BeforeEach
    void setUp() {
        uzivatel = new Uzivatel("Jan", "jan123", "heslo123");
        controller = new NastaveniController();

        controller.setSu(new SpravceUzivatelu(), uzivatel);
    }

    @Test
    void ulozit() {
        controller.ulozit();

        assertEquals("Petr", uzivatel.getJmeno());
        assertEquals("petr456", uzivatel.getUzivatelsakeJmeno());
        assertEquals("tajne123", uzivatel.getHeslo());
    }

    @Test
    void vymazat() {

        uzivatel.getSeznamTransakci().add(new Transakce(LocalDate.now(), 50, "cafe", TypTransakce.VYDAJ));
        uzivatel.getSeznamTransakci().add(new Transakce(LocalDate.now(), 235, "plat", TypTransakce.PRIJEM));

        assertEquals(2, uzivatel.getSeznamTransakci().size());

        controller.vymazat();

        assertEquals(0, uzivatel.getSeznamTransakci().size());
    }
}



