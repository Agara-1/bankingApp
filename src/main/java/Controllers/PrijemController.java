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
import Models.Transakce;
import Models.TypTransakce;
import sample.bankingapp.Uzivatel;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Třída sloužící k přidání nebo úpravě měsíčního příjmu uživatele.
 * Uživatel zde zadává výši svého měsíčního příjmu, která je následně
 * uložena.
 * Třída zahrnuje validaci vstupu a serializaci dat pro uchování změn.
 */

@SuppressWarnings("CallToPrintStackTrace")
public class PrijemController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;

    /**
     * Prvky používané v okně.
     */
    @FXML
    private Text chybaText;

    @FXML
    private Button backButton;


    @FXML
    private TextField mesicniPrijem_TextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField typPrijmu_TextField;
    /**
     * Částka, kterou chce uživatel pridat.
     */
    private int prijem;

    /**
     * Zkontroluje, zda uživatel zadal všechny požadované hodnoty a zda mají správný formát.
     * Ověřuje například:
     * - zda částka není prázdná a je kladné číslo,
     * - zda číslo účtu odpovídá očekávanému formátu,
     * - zda byla vybrána kategorie.
     * Pokud některá hodnota chybí nebo je neplatná, metoda zabrání pokračování a zobrazí chybové hlášení.
     */
    private boolean kontrola() {
        if (mesicniPrijem_TextField.getText().isEmpty() || typPrijmu_TextField.getText().isEmpty()) {
            chybaText.setText("Musíte zadat všechny hodnoty.");
            return false;
        }


        try {
            prijem = Integer.parseInt(mesicniPrijem_TextField.getText());
        } catch (NumberFormatException e) {
            chybaText.setText("Příjem musí být celé číslo.");
            return false;
        }

        if (prijem <= 0) {
            chybaText.setText("Příjem musí být kladné číslo.");
            return false;
        }
        return true;
    }

    /**
     * Provede automatický přechod do okna s přehledem transakcí po úspěšném odeslání peněz.
     * Tato metoda se volá po pridani prijmu.
     */
    private void prechod() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/homePage.fxml"));
            Parent root = loader.load();
            HomePageController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) pridatButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Hlavní metoda pro přidání měsíčního příjmu na účet uživatele.
     * Postupně provádí následující kroky:
     * - Přičte zadanou částku k aktuálnímu zůstatku uživatele,
     * - vytvoří novou příjmovou transakci a přidá ji do seznamu transakcí,
     * - aktualizuje příslušná data a serializuje je pro trvalé uložení.
     */
    @FXML
    void prijem() {
        if(kontrola()){
            int zustatek = aktualniUzivatel.getZustatek() + prijem;
            aktualniUzivatel.setMesicniPrijem(prijem);
            aktualniUzivatel.setZustatek(zustatek);
            Transakce transakce = new Transakce(LocalDate.now(), prijem, typPrijmu_TextField.getText(), TypTransakce.PRIJEM);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/homePage.fxml"));
            Parent root = loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {

    }
    /**
     * Nastaví aktuálního přihlášeného uživatele a instanci správce uživatelů.
     * Tato metoda se používá pro předání dat mezi jednotlivými částmi aplikace,
     * například při přechodu mezi okny nebo při načtení uživatelských dat.
     *
     * @param uz Aktuálně přihlášený uživatel
     * @param su  Instance třídy  SpravceUzivatelu, která spravuje seznam uživatelů
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

    }
}
