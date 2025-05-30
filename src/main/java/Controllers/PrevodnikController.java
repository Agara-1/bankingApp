package Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Models.Mena;
import Models.SpravceUzivatelu;
import sample.bankingapp.Uzivatel;
/**
 * Třída zajišťující převod měn mezi CZK, USD, EUR a GBP.
 * Umožňuje převody:
 * - z CZK do cizích měn USD, EUR, GBP
 * - z cizích měn do CZK,
 * - mezi cizími měnami navzájem např. USD do EUR.
 */

@SuppressWarnings("CallToPrintStackTrace")
public class PrevodnikController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;

    /**
     * Prvky používané v okně.
     */
    @FXML
    private ComboBox<Mena> mena_1_ComboBox;

    @FXML
    private ComboBox<Mena> mena_2_ComboBox;

    @FXML
    private Label castkaPo_Label;

    @FXML
    private TextField castkaPred_TextField;

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

    /**
     * Zobrazí pevně stanovený kurz 1 CZK vůči ostatním měnám USD, EUR, GBP.
     * Slouží k informativnímu zobrazení, kolik jednotek cizí měny odpovídá jedné české koruně.
     */
    public double zCZKDo(Mena mena) {
        return switch (mena) {
            case CZK -> 1.0;
            case USD -> 0.045;
            case EUR -> 0.041;
            case GBP -> 0.035;
        };

    }
    /**
     * Zobrazí pevně stanovený kurz 1 jednotky cizí měny vůči české koruně (CZK).
     * Například:
     * - 1 USD = 22 CZK
     * Slouží k informativnímu přehledu aktuálně používaných pevných kurzů v aplikaci.
     */
    public double doCZK(Mena mena) {
        return switch (mena) {
            case CZK -> 1.0;
            case USD -> 22.0;
            case EUR -> 24.5;
            case GBP -> 28.3;
        };
    }
    /**
     * Provede převod měny mezi vybranými měnami na základě částky zadané uživatelem.
     * Výsledek převodu je zobrazen v uživatelském rozhraní okna aplikace.
     * Používá pevně stanovené kurzy mezi měnami (např. CZK, USD, EUR, GBP).
     * Metoda byla částečně vytvořena s pomocí umělé inteligence ChatGPT.
     */
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

    /**
     * Přepne na nové okno (scénu) po kliknutí na tlačítko.
     * <p>
     * Tato metoda načte nové FXML okno, získá jeho controller,
     * a zavolá na něm metodu setSu() pomocí které předá aktuálního uživatele.
     * Následně nastaví nové okno jako aktivní scénu.
     * Po stisknutí tlačítka se zároveň uživatel odhlásí a je přesměrován na přihlašovací obrazovku.
     */
    @FXML
    void logoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setSu(su,aktualniUzivatel);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void info() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/info.fxml"));
            Parent root = loader.load();
            InfoController infoController = loader.getController();
            infoController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) infoButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void nastaveni() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/nastaveni.fxml"));
            Parent root = loader.load();
            NastaveniController nastaveniController = loader.getController();
            nastaveniController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) nastaveniButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void prevodnik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/prevodnik.fxml"));
            Parent root = loader.load();
            PrevodnikController prevodnikController = loader.getController();
            prevodnikController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) prevodnikButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void rozpocet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) rozpocetButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void transakce() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            transakceController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) transakceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
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
    void home() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/homePage.fxml"));
            Parent root = loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Naplní ComboBox všemi dostupnými měnami CZK, USD, EUR, GBP pro výběr uživatelem.
     * Výchozí hodnoty jsou nastaveny na:
     * - CZK jako výchozí zdrojová měna,
     * - USD jako výchozí cílová měna.
     * Metoda slouží k inicializaci výběru měn v uživatelském rozhraní aplikace.
     */
    @FXML
    void initialize() {
        mena_1_ComboBox.getItems().addAll(Mena.values());
        mena_2_ComboBox.getItems().addAll(Mena.values());
        mena_1_ComboBox.setValue(Mena.CZK);
        mena_2_ComboBox.setValue(Mena.USD);
    }
    /**
     * Nastaví aktuálního uživatelea spravceUzivatelu.
     * Tato metoda se používá při přechodu mezi okny, aby zajistila, že
     * se veškeré informace spojené s uživatelem správně zobrazí.
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }
}
