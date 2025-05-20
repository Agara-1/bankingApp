package sample.bankingapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }

    @FXML
    private Text chybaText;

    @FXML
    private Button signupButton;

    @FXML
    private TextField username_TextField;

    @FXML
    private Button mainLogInButton;

    @FXML
    private TextField password_TextField;


    @FXML
    void logIn() {
        String username = username_TextField.getText().trim();
        String password = password_TextField.getText().trim();
        boolean prihlaseniUspesne = false;
        for (Uzivatel uz : su.getSeznamUzivatelu()) {
            if ((username.equals(uz.getUzivatelsakeJmeno())) && (password.equals(uz.getHeslo()))) {
                this.aktualniUzivatel = uz;
                prihlaseniUspesne = true;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
                    Parent root = loader.load();
                    HomePageController homePageController = loader.getController();
                    homePageController.setSu(su, aktualniUzivatel);
                    Stage stage = (Stage) mainLogInButton.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }break;
            }
        }
        if (!prihlaseniUspesne) {
            chybaText.setText("Špatné uživatelské heslo nebo jmeno");
        }
    }


    @FXML
    void initialize() {


    }


    @FXML
    void swichToSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();
            SignUpController controller = loader.getController();
            controller.setSu(su);
            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
