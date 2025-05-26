package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NastaveniController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton infoButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    @FXML
    private TextField hesloNaUpravu;

    @FXML
    private ComboBox<Mena> menaNaUpravu_ComboBox;

    @FXML
    private TextField jmenoNaUpravu;

    @FXML
    private TextField usernameNaUpravu;

    @FXML
    private Button vymazatButton;


    @FXML
    void vymazat() {
        aktualniUzivatel.getSeznamTransakci().clear();
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

    public void nactiHodnoty() {
        jmenoNaUpravu.setPromptText(aktualniUzivatel.getJmeno());
        usernameNaUpravu.setPromptText(aktualniUzivatel.getUzivatelsakeJmeno());
        hesloNaUpravu.setPromptText(aktualniUzivatel.getHeslo());
        menaNaUpravu_ComboBox.setValue(aktualniUzivatel.getMena());

    }

    @FXML
    void ulozit() {
        String jmeno = jmenoNaUpravu.getText();
        String username = usernameNaUpravu.getText();
        String heslo = hesloNaUpravu.getText();

        if (!jmeno.isBlank()) {
            aktualniUzivatel.setJmeno(jmeno);
        }
        if (!username.isBlank()) {
            aktualniUzivatel.setUzivatelsakeJmeno(usernameNaUpravu.getText());
        }
        if (!heslo.isBlank()) {
            aktualniUzivatel.setHeslo(hesloNaUpravu.getText());
        }
        if (menaNaUpravu_ComboBox.getValue() != null) {
            aktualniUzivatel.setMena(menaNaUpravu_ComboBox.getValue());
        }
        su.serializaceUzivatelu();
    }

    @FXML
    void initialize() {

    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

        menaNaUpravu_ComboBox.getItems().addAll(Mena.values());
        menaNaUpravu_ComboBox.setValue(aktualniUzivatel.getMena());
        nactiHodnoty();
    }
}
