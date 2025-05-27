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

/**
 * Třída zodpovědná za přihlášení uživatele.
 * Provádí ověření zadaného uživatelského jména a hesla.
 * Pokud existuje uživatel se zadaným jménem a heslem v seznamu uživatelů,
 * přihlásí daného uživatele.
 */
public class LoginController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    /**
     * Prvky používané v okně HomePage.
     */
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

    /**
     * Pokusí se přihlásit uživatele na základě zadaného jména a hesla.
     * Porovná zadané údaje s existujícími uživateli v seznamu.
     * Pokud se shodují, uživatel je přihlášen a automaticky se otevře hlavní okno (homePage).
     * Pokud údaje nesedí, zobrazí se chybová zpráva.
     */
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

    /**
     * Přepne na nové okno (SignUp) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     */
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

    /**
     * Nastaví aktuálního uživatele která aktualizuje
     * zobrazení zůstatku a měsíčního příjmu.
     * <p>
     * Tato metoda se používá při přechodu mezi okny, aby zajistila, že
     * se veškeré informace spojené s uživatelem správně zobrazí.
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }
    public Uzivatel getAktualniUzivatel() {
        return aktualniUzivatel;
    }
}
