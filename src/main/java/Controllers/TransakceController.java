package Controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Models.SpravceUzivatelu;
import Models.Transakce;
import sample.bankingapp.Uzivatel;

/**
 * Třída slouží pro zpracování transakcí v rámci aplikace.
 * Uchovává seznam transakcí, které uživatel provedl, a poskytuje
 * metody pro přidávání, získávání nebo filtrování těchto transakcí.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class TransakceController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;

    /**
     * Prvky používané v okně.
     */
    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton homeButton;

    @FXML
    private ToggleButton infoButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    @FXML
    private ListView<Transakce> seznamTransakci_listview;

    @FXML
    private Button pridatTransakciButton;

    /**
     * Přepne na nové okno po kliknutí na tlačítko.
     * Tato metoda načte nové FXML okno určené pro přidávání transakcí,
     * získá jeho controller a zavolá na něm metodu setSu(),
     * pomocí které předá správce uživatelů a aktuálního uživatele.
     * Následně nastaví nově načtené okno jako aktivní scénu.
     */

    @FXML
    void poslatPenize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/pridatTransakci.fxml"));
            Parent root = loader.load();
            PridatTransakciController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) pridatTransakciButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Přepne na nové okno po kliknutí na tlačítko.
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
            loginController.setSu(su, aktualniUzivatel);
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
     * Načte seznam transakcí aktuálního uživatele a zobrazí je v komponentě ListView.
     * Metoda převádí seznam transakcí uživatele na ObservableList a nastavuje ho
     * do `seznamTransakci_listview`. Pomocí `setCellFactory` definuje vlastní vzhled
     * každé položky v seznamu, včetně zobrazení data, typu transakce, kategorie a částky.
     * Příjmy jsou barevně odlišeny zeleně a výdaje červeně.
     * Tato metoda byla vytvořena s pomocí umělé inteligence.
     */
    private void nactiTransakce() {
        ObservableList<Transakce> seznam = FXCollections.observableArrayList(aktualniUzivatel.getSeznamTransakci());
        seznamTransakci_listview.setItems(seznam);
        seznamTransakci_listview.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Transakce t, boolean empty) {
                super.updateItem(t, empty);

                if (empty || t == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String datum = t.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    String typ = t.getTypTransakce().toString();
                    String kategorie = "";
                    if (t.getKategorieVydaj() == null) {
                        kategorie = String.valueOf(t.getKategoriePrijem());
                    } else if (t.getKategoriePrijem() == null) {
                        kategorie = String.valueOf(t.getKategorieVydaj());
                    }

                    String castka = String.valueOf(t.getCastka());

                    Label datumLabel = new Label(datum);
                    Label typLabel = new Label(typ);
                    Label kategorieLabel = new Label(kategorie);
                    Label castkaLabel = new Label(castka);

                    datumLabel.setPrefWidth(160);
                    typLabel.setPrefWidth(90);
                    kategorieLabel.setPrefWidth(120);
                    castkaLabel.setPrefWidth(150);

                    datumLabel.setStyle("-fx-font-family: 'Calibri Light'; -fx-font-size: 20");
                    typLabel.setStyle("-fx-font-family: 'Calibri Light'; -fx-font-size: 20");
                    kategorieLabel.setStyle("-fx-font-family: 'Calibri Light'; -fx-font-size: 20");
                    castkaLabel.setStyle("-fx-font-family: 'Calibri Light'; -fx-font-size: 20");

                    if (typ.equalsIgnoreCase("PRIJEM")) {
                        castkaLabel.setStyle("-fx-text-fill: green; -fx-font-size: 22");
                    } else {
                        castkaLabel.setStyle("-fx-text-fill: red; -fx-font-size: 22");
                    }

                    HBox box = new HBox(10, datumLabel, kategorieLabel, typLabel, castkaLabel);
                    box.setAlignment(Pos.CENTER_LEFT);
                    box.setPadding(new Insets(5, 10, 5, 10));

                    setGraphic(box);
                }
            }
        });
    }

    @FXML
    void initialize() {


    }

    /**
     * Nastaví aktuálního uživatele a zavolá metodu nactiTransakce(),
     * která aktualizuje zobrazení seznamu transakci.
     * <p>
     * Tato metoda se používá při přechodu mezi okny, aby zajistila, že
     * se veškeré informace spojené s uživatelem správně zobrazí.
     */
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
        nactiTransakce();
    }
}
