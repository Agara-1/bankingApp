package sample.bankingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SignUpController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    public SignUpController() {
        this.su = new SpravceUzivatelu();
    }

    @FXML
    private Button mainSignUpButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField name_textField;

    @FXML
    private TextField password_textField;

    @FXML
    private TextField user_name_textField;

    @FXML
    private Text chybaText;

    @FXML
    void signUp() {
        Uzivatel uz = new Uzivatel(name_textField.getText(), user_name_textField.getText(), password_textField.getText());
        this.aktualniUzivatel = uz;
        if (!su.uzivatelExistuje(uz)) {
            su.getSeznamUzivatelu().add(uz);
            su.serializaceUzivatelu();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                LoginController controller = loader.getController();
                controller.setSu(su, aktualniUzivatel);
                Stage stage = (Stage) mainSignUpButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chybaText.setText("uzivatel uz existuje");

        }


    }


    @FXML
    void initialize() {

        loginButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root = loader.load();
                LoginController controller = loader.getController();
                controller.setSu(su, aktualniUzivatel);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void setSu(SpravceUzivatelu su) {
        this.su = su;

    }
}

