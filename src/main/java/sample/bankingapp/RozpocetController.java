package sample.bankingapp;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RozpocetController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    private int soucetJidlo = 0;
    private int soucetDoprava = 0;
    private int soucetZabava = 0;
    private int soucetBezny = 0;
    private int soucetOstatni = 0;

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


    public void rozpocitat() {

        int limitJidlo = aktualniUzivatel.getLimitJidlo();
        int limitDoprava = aktualniUzivatel.getLimitDoprava();
        int limitZabava = aktualniUzivatel.getLimitZabava();
        int limitBezny = aktualniUzivatel.getLimitBezny();
        int limitOstatni = aktualniUzivatel.getLimitOstatni();

        limitJidlo_Label.setText(String.valueOf(limitJidlo));
        limitDoprava_Label.setText(String.valueOf(limitDoprava));
        limitBezny_Label.setText(String.valueOf(limitBezny));
        limitZabava_Label.setText(String.valueOf(limitZabava));
        limitOstatni_Label.setText(String.valueOf(limitOstatni));

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
                        zbyvaOstatni_Label.setText(String.valueOf(limitOstatni - -soucetOstatni));
                        break;
                }

            }
        }

    }

    @FXML
    void logoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void info() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
            Parent root = loader.load();
            InfoController infoController = loader.getController();
            infoController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) infoButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void nastaveni() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nastaveni.fxml"));
            Parent root = loader.load();
            NastaveniController nastaveniController = loader.getController();
            nastaveniController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) nastaveniButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void prevodnik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("prevodnik.fxml"));
            Parent root = loader.load();
            PrevodnikController prevodnikController = loader.getController();
            prevodnikController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) prevodnikButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void upravitLimit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("upravitLimit.fxml"));
            Parent root = loader.load();
            UpravitLimitController upravitLimitController = loader.getController();
            upravitLimitController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) upravitLimitButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void rozpocet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) rozpocetButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void transakce() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            transakceController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) transakceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void home() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
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

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
        rozpocitat();
        progres();
    }

    public int getSoucetJidlo() {
        return soucetJidlo;
    }

    public int getSoucetDoprava() {
        return soucetDoprava;
    }

    public int getSoucetZabava() {
        return soucetZabava;
    }

    public int getSoucetBezny() {
        return soucetBezny;
    }

    public int getSoucetOstatni() {
        return soucetOstatni;
    }
}


