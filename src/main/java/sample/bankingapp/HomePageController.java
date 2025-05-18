package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class HomePageController {
    private Uzivatel aktualniUzivatel;
    private SpravceUzivatelu su;

    @FXML
    private PieChart graf;

    @FXML
    private ToggleGroup group1;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton infoButton;

    @FXML
    private Label kurz_label;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private Button pridatPrijemButton;

    @FXML
    private Button pridatVydajButton;

    @FXML
    private Label prijem_label;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    @FXML
    void homeButton() {

    }

    @FXML
    void infoButton() {

    }

    @FXML
    void nastaveniButton() {

    }

    @FXML
    void prevodnikButton() {

    }

    @FXML
    void rozpoce() {

    }

    @FXML
    void transakceButton() {

    }





    @FXML
    void initialize() {



//        homeButton.setOnAction(event -> {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
//                Parent root = loader.load();
//                Stage stage = (Stage) homeButton.getScene().getWindow();
//                stage.setScene(new Scene(root));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });



        infoButton.setOnAction(event -> {
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
      /*  pridatPrijemButton.setOnAction(event -> {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("prijem.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) pridatPrijemButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    } catch (IOException e) {
        e.printStackTrace();
    }
});

        pridatVydajButton.setOnAction(event -> {
            pridatVydajButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("rozpocet.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });*/
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

//        homeButton.setOnAction(e -> SceneManager.switchScene("homePage.fxml", homeButton));
//        infoButton.setOnAction(e -> SceneManager.switchScene("info.fxml", infoButton));
//        rozpocetButton.setOnAction(e -> SceneManager.switchScene("rozpocet.fxml", rozpocetButton));
//        transakceButton.setOnAction(e -> SceneManager.switchScene("transakce.fxml", transakceButton));
//        prevodnikButton.setOnAction(e -> SceneManager.switchScene("prevodnik.fxml", prevodnikButton));
//        nastaveniButton.setOnAction(e -> SceneManager.switchScene("nastaveni.fxml", nastaveniButton));
//        logoutButton.setOnAction(e -> SceneManager.switchScene("login.fxml", null));
    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }
}




