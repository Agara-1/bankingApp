package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.SpravceUzivatelu;
import sample.bankingapp.Uzivatel;

import java.io.IOException;
/**
 * Třída sloužící k registraci nových uživatelů do systému.
 * Umožňuje uživateli zadat požadované údaje, jako jsou:
 * - jméno,
 * - přihlašovací jméno,
 * - heslo.
 * Po ověření správnosti vstupů a dostupnosti přihlašovacího jména
 * je nový uživatel uložen do seznamu uživatelů a následně serializován
 * pro trvalé uložení.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class SignUpController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    public SignUpController() {
        this.su = new SpravceUzivatelu();
        this.aktualniUzivatel = su.getAktualniUzivatel();
    }
    /**
     * Prvky používané v okně.
     */

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
    /**
     * Provede přechod do dalšího okna po dokončení akce.
     * Používá se např. po registraci.
     */
    public void prechod() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) mainSignUpButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Ověřuje, zda uživatel zadal všechny požadované hodnoty při registraci.
     * Metoda kontroluje, zda parametry nejsou null ani prázdné.
     * Pokud některý z parametrů není správně vyplněn, vypíše chybovou hlášku do textového pole  chybaText.
     * @param jmeno      Jméno uživatele zadané ve formuláři.
     * @param uZJmeno    Uživatelské jméno zadané ve formuláři.
     * @param heslo      Heslo zadané ve formuláři.
     * @return           true, pokud jsou všechny hodnoty vyplněné a validní; jinak {@code false}.
     */
    public boolean kontrola(String jmeno,String uZJmeno,String heslo){
        if (jmeno.isEmpty() || uZJmeno.isEmpty() || heslo.isEmpty()) {
            chybaText.setText("Zadejte vsechny hodnoty");
            return false;
        }
        return true;
    }
    /**
     * Metoda zpracovává registraci nového uživatele.
     * Na základě údajů z textových polí vytvoří nového uživatele a ověří,
     * zda již neexistuje v systému. Pokud neexistuje, přidá ho do seznamu
     * uživatelů, uloží změny pomocí serializace a přepne na další scénu.
     * V opačném případě zobrazí chybovou zprávu, že uživatel již existuje.
     */
    @FXML
    void signUp() {
       if (kontrola(name_textField.getText(), user_name_textField.getText(), password_textField.getText())){
           Uzivatel uz = new Uzivatel(name_textField.getText(), user_name_textField.getText(), password_textField.getText());
           this.aktualniUzivatel = uz;
           if (!su.uzivatelExistuje(uz)) {
               su.getSeznamUzivatelu().add(uz);
               su.serializaceUzivatelu();
               prechod();
           } else {
               chybaText.setText("uzivatel uz existuje");

           }
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
    void login() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
    }

    /**
     * Nastaví správce uživatelů.
     * Metoda se používá při přechodu mezi scénami pro předání informací
     * o správci uživatelů a přihlášeném uživateli do nového controlleru.
     * @param su instance {@link SpravceUzivatelu}, která spravuje seznam všech uživatelů
     */
    public void setSu(SpravceUzivatelu su) {
        this.su = su;

    }
}

