package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PrevodnikController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    @FXML
    private ComboBox<Mena> mena_1_ComboBox;

    @FXML
    private ComboBox<Mena> mena_2_ComboBox;

    @FXML
    private Label castkaPo_Label;

    @FXML
    private TextField castkaPred_TextField;

    @FXML
    private ToggleGroup group1;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton infoButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private Button prevest;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    public double zCZKDo(Mena mena) {
        switch (mena) {
            case CZK:
                return 1.0;
            case USD:
                return 0.045;
            case EUR:
                return 0.041;
            case GBP:
                return 0.035;
            default:return 1.0;
        }

    }
    public double doCZK(Mena mena) {
        switch (mena) {
            case CZK:
                return 1.0;
            case USD:
                return 22.0;
            case EUR:
                return 24.5;
            case GBP:
                return 28.3;
            default:
                return 1.0;
        }
    }

    @FXML
    void prevest() {
        try {
double castka = Double.parseDouble(castkaPred_TextField.getText());
double vCZK = castka * doCZK(mena_1_ComboBox.getSelectionModel().getSelectedItem());
double vysledek = vCZK * zCZKDo(mena_2_ComboBox.getSelectionModel().getSelectedItem());
castkaPo_Label.setText(String.format("%.2f", vysledek));
        } catch (NumberFormatException e) {
            castkaPo_Label.setText("Neplatná částka.");
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
        mena_1_ComboBox.getItems().addAll(Mena.values());
        mena_2_ComboBox.getItems().addAll(Mena.values());
        mena_1_ComboBox.setValue(Mena.CZK);
        mena_2_ComboBox.setValue(Mena.USD);
    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }
}
