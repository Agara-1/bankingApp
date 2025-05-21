package sample.bankingapp;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RozpocetController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    @FXML
    private Button logoutButton;

    @FXML
    private Button upravitLimitButton;

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
    private Label kategorieBezna_Label;

    @FXML
    private Label kategorieDoprava_Label;

    @FXML
    private Label kategorieJidlo_Label;

    @FXML
    private Label kategorieOstatni_Label;

    @FXML
    private Label kategorieZabava_Label;


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


    public void rozpocitat() {
        int soucet = 0;
        int zakladniLimit = 1000;
        limitJidlo_Label.setText(String.valueOf(zakladniLimit));
        limitDoprava_Label.setText(String.valueOf(zakladniLimit));
        limitBezny_Label.setText(String.valueOf(zakladniLimit));
        limitZabava_Label.setText(String.valueOf(zakladniLimit));
        limitOstatni_Label.setText(String.valueOf(zakladniLimit));

        for (Transakce t : aktualniUzivatel.getSeznamTransakci()) {
            if (t.getKategorie().equals(kategorieJidlo_Label.getText())) {
                soucet+=t.getCastka();
                utracenoJidlo_Label.setText(soucet + "");
            }

        }


    }


    @FXML
    void logoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
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
    }
}


