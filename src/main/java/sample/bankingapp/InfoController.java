package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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

public class InfoController {

    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;



    @FXML
    private Button editButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button pridatPrijemButton;

    @FXML
    private Button pridatVydajButton;

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
    private Label CisloUctu;

    @FXML
    private Label datumText;
    @FXML
    private Label typUctu;

    @FXML
    private Label usernameInfoText;

    @FXML
    private Label jmenoUzivatelText;

    private void nactiData() {
        jmenoUzivatelText.setText(aktualniUzivatel.getJmeno());
        usernameInfoText.setText(aktualniUzivatel.getUzivatelsakeJmeno());
        CisloUctu.setText(aktualniUzivatel.generaceUctu());
        datumText.setText(aktualniUzivatel.getDatumZalozeni().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }


    @FXML
    void initialize() {




        homeButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) homeButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        infoButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) infoButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        rozpocetButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("rozpocet.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) rozpocetButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        transakceButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) transakceButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        prevodnikButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("prevodnik.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) prevodnikButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        nastaveniButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("nastaveni.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) nastaveniButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        logoutButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
        nactiData();
    }



}
