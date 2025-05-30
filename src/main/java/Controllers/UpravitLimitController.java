package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Models.KategorieTransakci;
import Models.SpravceUzivatelu;
import sample.bankingapp.Uzivatel;

import java.io.IOException;

/**
 * Třída zodpovědná za úpravu rozpočtového limitu pro konkrétní kategorii výdajů.
 * Uživatel si může zvolit jednu z dostupných kategorií výdajů např. jídlo, doprava, zábava
 * a nastavit pro ni nový měsíční limit. Třída načte aktuálního uživatele, zpracuje
 * jeho volbu a aktualizuje odpovídající hodnotu v objektu.
 * Používá komponenty JavaFX pro zobrazení rozhraní, získání vstupu od uživatele a potvrzení změny.
 * Tato třída byla vytvořena s pomocí umělé inteligence (AI).
 */
@SuppressWarnings("CallToPrintStackTrace")
public class UpravitLimitController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    /**
     * Prvky používané v okně.
     */
    @FXML
    private Button backButton;

    @FXML
    private TextField castkaNaPoslani_TextField;

    @FXML
    private Text chybaText;

    @FXML
    private ComboBox<KategorieTransakci> kategorieVydaje_ComboBox;

    @FXML
    private Button potvrditButton;

    /**
     * Přepne zpět na předchozí okno (scénu) po kliknutí na tlačítko.
     * Tato metoda slouží k návratu na předchozí obrazovku, například pokud si uživatel
     * přál vrátit se z aktuálního okna. Při přepnutí se znovu načte předchozí FXML scéna,
     * nastaví se její controller a předá se mu aktuální uživatel pomocí metody setSu().
     */
    @FXML
    void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) backButton.getScene().getWindow();
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
    private void prechod() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) potvrditButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean kontrola(int castkaCislo, KategorieTransakci kategorieVydaje) {

        if (kategorieVydaje == null || castkaCislo == 0) {
            chybaText.setText("Vyber kategorii a zadej částku.");
            return false;
        } else if (castkaCislo <= 0) {
            chybaText.setText("Částka musí být kladné číslo.");
            return false;
        }
//        try {
//
//        } catch (NumberFormatException e) {
//            chybaText.setText("Částka musí být celé číslo.");
//            return false;
//        }
        return true;
    }

    /**
     * Zpracuje potvrzení nového limitu pro vybranou kategorii výdajů.
     * Tato metoda:
     * <ul>
     *     <li>ověří, zda byla vybrána kategorie a zadána částka,</li>
     *     <li>zkontroluje, že zadaná částka je platné celé a kladné číslo,</li>
     *     <li>uloží nový limit pro příslušnou kategorii výdajů aktuálního uživatele,</li>
     *     <li>provede serializaci aktualizovaných dat uživatele,</li>
     *     <li>a nakonec provede přechod do dalšího okna pomocí metody prechod().</li>
     * </ul>
     * V případě neplatného vstupu zobrazí odpovídající chybovou zprávu.
     */
    @FXML
    void potvrdit() {
        int castka = Integer.parseInt(castkaNaPoslani_TextField.getText()) ;
        KategorieTransakci kategorie = kategorieVydaje_ComboBox.getValue();
        if(kontrola(castka,kategorie)){
            switch (kategorie) {
                case JIDLO:
                    aktualniUzivatel.setLimitJidlo(castka);
                    break;
                case DOPRAVA:
                    aktualniUzivatel.setLimitDoprava(castka);
                    break;
                case ZABAVA:
                    aktualniUzivatel.setLimitZabava(castka);
                    break;
                case BEZNE_VYDAJE:
                    aktualniUzivatel.setLimitBezny(castka);
                    break;
                case OSTATNI:
                    aktualniUzivatel.setLimitOstatni(castka);
                    break;
            }
            su.serializaceUzivatelu();
            prechod();
        }
    }

    /**
     * Inicializační metoda, která se automaticky volá při načtení okna.
     * Naplní ComboBox kategoriemi výdajů všemi hodnotami výčtu KategorieTransakci,
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
