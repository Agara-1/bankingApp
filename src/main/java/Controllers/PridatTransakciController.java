package Controllers;

import java.io.IOException;
import java.time.LocalDate;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.bankingapp.Uzivatel;

/**
 * Třída zodpovědná za přidávání nových transakcí uživatele.
 * Uživatel zadává:
 * - částku, kterou chce odeslat,
 * - číslo účtu příjemce,
 * - kategorii, pod kterou transakce spadá např. Jídlo, Doprava, Ostatní.
 * Třída validuje zadané údaje a následně transakci přidá do seznamu transakcí uživatele.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class PridatTransakciController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    /**
     * Prvky používané v okně.
     */
    @FXML
    private TextField cisloUctuNaPoslani_TextField;

    @FXML
    private Button backButton;

    @FXML
    private TextField castkaNaPoslani_TextField;

    @FXML
    private Button poslatButton;

    @FXML
    private Text chybaText;

    @FXML
    private ComboBox<KategorieTransakci> kategorieVydaje_ComboBox;
    /**
     * Částka, kterou chce uživatel odeslat v rámci transakce.
     */
    private int vydaj;

    /**
     * Zkontroluje, zda uživatel zadal všechny požadované hodnoty a zda mají správný formát.
     * Ověřuje například:
     * - zda částka není prázdná a je kladné číslo,
     * - zda číslo účtu odpovídá očekávanému formátu,
     * - zda byla vybrána kategorie.
     * Pokud některá hodnota chybí nebo je neplatná, metoda zabrání pokračování a zobrazí chybové hlášení.
     */
    private boolean kontrola() {

        try {
            vydaj = Integer.parseInt(castkaNaPoslani_TextField.getText());
        } catch (NumberFormatException e) {
            chybaText.setText("Částka musí být celé číslo.");
        }
        if (!cisloUctuNaPoslani_TextField.getText().matches("^\\d{4} \\d{4} \\d{4}$")) {
            chybaText.setText("Číslo účtu musí být ve formátu XXXX XXXX XXXX.");
            return false;
        }
        if (vydaj <= 0) {
            chybaText.setText("Částka musí být kladné číslo.");
            return false;
        }
        if (vydaj > aktualniUzivatel.getZustatek()) {
            chybaText.setText("Nedostatek prostředků na účtu.");
            return false;
        }
        if (castkaNaPoslani_TextField.getText().isEmpty() || cisloUctuNaPoslani_TextField.getText().isEmpty() || kategorieVydaje_ComboBox.getValue() == null) {
            chybaText.setText("Musíte zadat všechny hodnoty.");
            return false;
        }

        return true;
    }

    /**
     * Provede automatický přechod do okna s přehledem transakcí po úspěšném odeslání peněz.
     * Tato metoda se volá po dokončení transakce, aby uživateli okamžitě zobrazila aktuální stav,
     * včetně nově přidané transakce.
     */
    private void prechod() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/transakce.fxml"));
            Parent root = loader.load();
            TransakceController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) poslatButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hlavní metoda pro zpracování nové transakce.
     * Postupně provádí následující kroky:
     * - Ověří vstupní hodnoty pomocí metody kontrola(),
     * - pokud jsou platné, vytvoří novou transakci a uloží ji do seznamu transakcí uživatele,
     * - následně seznam transakcí serializuje (uloží do souboru),
     * - a nakonec automaticky přejde do okna s přehledem transakcí pomocí metody prechod().
     */
    @FXML
    void poslat() {
       if (kontrola()){
           int zustatek = aktualniUzivatel.getZustatek() - vydaj;
           aktualniUzivatel.setZustatek(zustatek);
           Transakce transakce = new Transakce(LocalDate.now(), vydaj, kategorieVydaje_ComboBox.getValue(), TypTransakce.VYDAJ);
           aktualniUzivatel.pridaniTransakce(transakce);
           su.serializaceUzivatelu();
           prechod();
       }

    }

    /**
     * Přepne zpět na předchozí okno (scénu) po kliknutí na tlačítko.
     * Tato metoda slouží k návratu na předchozí obrazovku, například pokud si uživatel
     * přál vrátit se z aktuálního okna. Při přepnutí se znovu načte předchozí FXML scéna,
     * nastaví se její controller a předá se mu aktuální uživatel pomocí metody setSu().
     */
    @FXML
    void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            transakceController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Naplní ComboBox kategoriemi transakcí, ze kterých si uživatel může vybrat.
     */
    @FXML
    void initialize() {
        kategorieVydaje_ComboBox.getItems().addAll(KategorieTransakci.values());
    }

    /**
     * Tato metoda se používá při přechodu mezi okny, aby zajistila, že
     * se veškeré informace spojené s uživatelem správně zobrazí.
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

    }
}
