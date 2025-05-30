package sample.bankingapp.Testovani;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controllers.NastaveniController;
import Models.SpravceUzivatelu;
import sample.bankingapp.Uzivatel;

import static org.junit.jupiter.api.Assertions.*;

class NastaveniControllerTest {
    private NastaveniController controller;
    private Uzivatel uzivatel;
    @BeforeEach
    void setUp() {
        uzivatel = new Uzivatel("Jan", "jan123", "heslo123");
        controller = new NastaveniController();

        controller.setSu(new SpravceUzivatelu(), uzivatel);
    }

    @Test
    void vymazat() {
    }

    @Test
    void ulozit() {
        controller.ulozit();

        assertEquals("Jan", uzivatel.getJmeno());
        assertEquals("jan123", uzivatel.getUzivatelsakeJmeno());
        assertEquals("heslo123", uzivatel.getHeslo());
    }
}