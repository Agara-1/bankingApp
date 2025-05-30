package Controllers;


import java.io.IOException;

import Models.SpravceUzivatelu;
import Models.Transakce;
import Models.TypTransakce;
import sample.bankingapp.Uzivatel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Třída sloužící ke sledování a správě osobního rozpočtu uživatele.
 * Zobrazuje:
 * - kolik peněz uživatel utratil,
 * - za jaké kategorie byly výdaje provedeny,
 * - jaký je nastavený měsíční rozpočtový limit,
 * - a kolik peněz zbývá do konce limitu.
 * Třída pomáhá uživateli lépe spravovat své finance a mít přehled
 * o výdajích v různých kategoriích.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class RozpocetController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    private int soucetJidlo = 0;
    private int soucetDoprava = 0;
    private int soucetZabava = 0;
    private int soucetBezny = 0;
    private int soucetOstatni = 0;
    private int limitJidlo;
    private int limitDoprava;
    private int limitZabava;
    private int limitBezny;
    private int limitOstatni;
    /**
     * Prvky používané v okně.
     */
    @FXML
    private Button upravitLimitButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton infoButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    @FXML
    private ProgressBar progresRozpocet_ProgressBar;

    @FXML
    private Label limitBezny_Label;

    @FXML
    private Label limitDoprava_Label;

    @FXML
    private Label limitJidlo_Label;

    @FXML
    private Label limitOstatni_Label;

    @FXML
    private Label limitZabava_Label;

    @FXML
    private Label utracenoBezne_Label;

    @FXML
    private Label utracenoDoprava_Label;

    @FXML
    private Label utracenoJidlo_Label;

    @FXML
    private Label utracenoOstatni_Label;

    @FXML
    private Label utracenoZabava_Label;

    @FXML
    private Label zbyvaBezne_Label;

    @FXML
    private Label zbyvaDoprava_Label;

    @FXML
    private Label zbyvaJidlo_Label;

    @FXML
    private Label zbyvaOstatni_Label;

    @FXML
    private Label zbyvaZabava_Label;

    /**
     * Nastaví hodnotu do progress baru na základě výše utracených peněz
     * vůči měsíčnímu příjmu uživatele.
     * Tato metoda vizuálně zobrazuje, kolik procent z měsíčního příjmu
     * již bylo utraceno, a tím pomáhá uživateli sledovat jeho rozpočet.
     */
    public void progres() {

        int celkovyVydaje = 0;
        double prog = 0;

        for (Transakce t : aktualniUzivatel.getSeznamTransakci()) {
            if (t.getTypTransakce() == TypTransakce.VYDAJ) {
                celkovyVydaje += t.getCastka();
            }
        }

        if (aktualniUzivatel.getMesicniPrijem() > 0) {
            prog = (double) celkovyVydaje / aktualniUzivatel.getMesicniPrijem();
        }

        progresRozpocet_ProgressBar.setProgress(Math.min(prog, 1.0));
    }

    /**
     * Zapíše všechny potřebné hodnoty do tabulky pro zobrazení rozpočtu uživatele.
     * - částky, které uživatel utratil,
     * - kategorie, za které byly peníze utraceny,
     * - měsíční limit rozpočtu,
     * - a zůstatek, kolik může uživatel ještě utratit.
     * Tato metoda zajišťuje, že má uživatel jasný přehled o svých financích
     * a může lépe plánovat další výdaje.
     */
    public void znazorneniRozpoctu() {
        for (Transakce t : aktualniUzivatel.getSeznamTransakci()) {
            if (t.getTypTransakce() == TypTransakce.VYDAJ && t.getKategorieVydaj() != null) {
                switch (t.getKategorieVydaj()) {
                    case JIDLO:
                        soucetJidlo += t.getCastka();
                        utracenoJidlo_Label.setText(String.valueOf(soucetJidlo));
                        zbyvaJidlo_Label.setText(String.valueOf(limitJidlo - soucetJidlo));
                        break;
                    case DOPRAVA:
                        soucetDoprava += t.getCastka();
                        utracenoDoprava_Label.setText(String.valueOf(soucetDoprava));
                        zbyvaDoprava_Label.setText(String.valueOf(limitDoprava - soucetDoprava));
                        break;
                    case ZABAVA:
                        soucetZabava += t.getCastka();
                        utracenoZabava_Label.setText(String.valueOf(soucetZabava));
                        zbyvaZabava_Label.setText(String.valueOf(limitZabava - soucetZabava));
                        break;
                    case BEZNE_VYDAJE:
                        soucetBezny += t.getCastka();
                        utracenoBezne_Label.setText(String.valueOf(soucetBezny));
                        zbyvaBezne_Label.setText(String.valueOf(limitBezny - soucetBezny));
                        break;
                    case OSTATNI:
                        soucetOstatni += t.getCastka();
                        utracenoOstatni_Label.setText(String.valueOf(soucetOstatni));
                        zbyvaOstatni_Label.setText(String.valueOf(limitOstatni - soucetOstatni));
                        break;
                }
            }
        }
    }
    /**
     * Zapíše všechny potřebné hodnoty do tabulky rozpočtu a následně zavolá metodu  znazorneniRozpoctu().
     * Tato metoda:
     * - naplní tabulku daty o výdajích uživatele
     * - zobrazí aktuální stav měsíčního limitu a zbývajícího rozpočtu.
     * Slouží k tomu, aby měl uživatel komplexní přehled o svých financích na jednom místě.
     */
    public void rozpocitat() {
        limitJidlo_Label.setText(String.valueOf(limitJidlo));
        limitDoprava_Label.setText(String.valueOf(limitDoprava));
        limitBezny_Label.setText(String.valueOf(limitBezny));
        limitZabava_Label.setText(String.valueOf(limitZabava));
        limitOstatni_Label.setText(String.valueOf(limitOstatni));

        znazorneniRozpoctu();
    }

    /**
     * Přepne na nové okno po kliknutí na tlačítko.
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     * Po stisknutí tlačítka se zároveň uživatel odhlásí a je přesměrován na přihlašovací obrazovku.
     */
    @FXML
    void logoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void info() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/info.fxml"));
            Parent root = loader.load();
            InfoController infoController = loader.getController();
            infoController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) infoButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void nastaveni() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/nastaveni.fxml"));
            Parent root = loader.load();
            NastaveniController nastaveniController = loader.getController();
            nastaveniController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) nastaveniButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void prevodnik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/prevodnik.fxml"));
            Parent root = loader.load();
            PrevodnikController prevodnikController = loader.getController();
            prevodnikController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) prevodnikButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * Tato metoda načte nové FXML okno, získá jeho controller
     * a zavolá na něm metodu setSu(), pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     * Nové okno slouží k úpravě měsíčního limitu pro vybranou kategorii rozpočtu,
     * což uživateli umožňuje lépe kontrolovat své výdaje.
     */
    @FXML
    void upravitLimit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/upravitLimit.fxml"));
            Parent root = loader.load();
            UpravitLimitController upravitLimitController = loader.getController();
            upravitLimitController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) upravitLimitButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void rozpocet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) rozpocetButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void transakce() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            transakceController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) transakceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
    @FXML
    void home() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/homePage.fxml"));
            Parent root = loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }
    /**
     * Nastaví aktuálního uživatele a správce uživatelů,
     * poté zavolá metody  rozpocitat() a progres().
     * Tato metoda zajistí, že se po přechodu mezi okny správně načtou a zobrazí
     * aktuální limity rozpočtu přihlášeného uživatele, a zároveň se aktualizuje
     * vizuální znázornění.
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
        this.limitJidlo = aktualniUzivatel.getLimitJidlo();
        this.limitDoprava = aktualniUzivatel.getLimitDoprava();
        this.limitZabava = aktualniUzivatel.getLimitZabava();
        this.limitBezny = aktualniUzivatel.getLimitBezny();
        this.limitOstatni = aktualniUzivatel.getLimitOstatni();
        rozpocitat();
        progres();
    }

}


