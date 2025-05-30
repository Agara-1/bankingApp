package Controllers;

import java.io.IOException;

import Models.SpravceUzivatelu;
import Models.Transakce;
import Models.TypTransakce;
import sample.bankingapp.Uzivatel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * Controller, který ovládá hlavní okno celé aplikace.
 * <p>
 * Umožňuje uživateli:
 * <ul>
 *     <li>otevřít okno pro přidání příjmu,</li>
 *     <li>přidat měsíční příjem,</li>
 *     <li>zobrazit aktuální zůstatek,</li>
 *     <li>prohlížet graf výdajů podle kategorií.</li>
 * </ul>
 * Graf slouží k vizualizaci informací o tom, kolik a za co uživatel utratil.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class HomePageController {
    private Uzivatel aktualniUzivatel;
    private SpravceUzivatelu su;
    private int jidlo = 0;
    private int doprava = 0;
    private int zabava = 0;
    private int bezny = 0;
    private int ostatni = 0;

    @FXML
    private PieChart graf_Piechart;
    /**
     * Prvky používané v okně HomePage.
     */
    @FXML
    private ToggleButton infoButton;

    @FXML
    private Button logoutButton;

    @FXML
    private ToggleButton nastaveniButton;

    @FXML
    private ToggleButton prevodnikButton;

    @FXML
    private Button pridatPrijemButton;

    @FXML
    private Label prijem_label;

    @FXML
    private ToggleButton rozpocetButton;

    @FXML
    private ToggleButton transakceButton;

    @FXML
    private Label zustatek;

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
     * Tato metoda načte nové FXML okno, získá jeho controller
     * a zavolá na něm metodu setSu(), pomocí které předá
     * aktuálního uživatele a správce uživatelů.
     * Poté nastaví nové okno jako aktivní scénu.
     * <p>
     * Konkrétně se přepne na okno, ve kterém může uživatel přidat
     * svůj měsíční příjem.
     */
    @FXML
    void pridatPrijemButton() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/bankingapp/pridatPrijem.fxml"));
            Parent root = loader.load();
            PrijemController prijemController = loader.getController();
            prijemController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) pridatPrijemButton.getScene().getWindow();
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


    @FXML
    void initialize() {

    }

    /**
     * Zapíše položky do grafu na základě jejich kategorií (např. Jídlo, Ostatní).
     * Tato metoda slouží k vizuálnímu zobrazení rozdělení výdajů nebo jiných dat podle kategorií
     * v grafické podobě pomocí  koláčového grafu.
     * Používá se pro přehled o rozpočtu.
     */
    public void nahravaniPolozekGrafu() {
        graf_Piechart.getData().clear();
        if (jidlo != 0) {
            graf_Piechart.getData().add(new PieChart.Data("Jidlo", jidlo));
        }
        if (doprava != 0) {
            graf_Piechart.getData().add(new PieChart.Data("Doprava", doprava));
        }
        if (zabava != 0) {
            graf_Piechart.getData().add(new PieChart.Data("Zabava", zabava));
        }
        if (bezny != 0) {
            graf_Piechart.getData().add(new PieChart.Data("Běžné výdaje", bezny));
        }
        if (ostatni != 0) {
            graf_Piechart.getData().add(new PieChart.Data("Ostatni", ostatni));
        }
        su.serializaceUzivatelu();
    }

    /**
     * Přiřadí hodnoty jednotlivým položkám v koláčovém grafu.
     * Tato metoda nastavuje konkrétní datové hodnoty pro každou kategorii,
     * která je zobrazena v koláčovém grafu (např. Jídlo, Doprava, Ostatní).
     * Slouží k vizuálnímu znázornění podílu jednotlivých kategorií
     * na celkových datech.
     */
    public void nahravanihodnotGrafu() {
        for (Transakce t : aktualniUzivatel.getSeznamTransakci()) {
            if (t.getTypTransakce() == TypTransakce.VYDAJ && t.getKategorieVydaj() != null) {
                switch (t.getKategorieVydaj()) {
                    case JIDLO:
                        jidlo += t.getCastka();
                        break;
                    case DOPRAVA:
                        doprava += t.getCastka();
                        break;
                    case ZABAVA:
                        zabava += t.getCastka();
                        break;
                    case BEZNE_VYDAJE:
                        bezny += t.getCastka();
                        break;
                    case OSTATNI:
                        ostatni += t.getCastka();
                        break;
                }
            }
        }
        su.serializaceUzivatelu();
    }

    /**
     * Aktualizuje zobrazení zůstatku a měsíčního příjmu na hlavním okně.
     * <p>
     * Tato metoda se volá po přidání nového měsíčního příjmu uživatelem.
     * Obnoví hodnoty v příslušných Label prvcích, aby zobrazovaly
     * aktuální zůstatek a měsíční příjem uživatele.
     * Zároveň nastaví nové hodnoty pro koláčový diagram, který vizualizuje rozložení výdajů
     * podle jednotlivých kategorií.
     */
    private void refreshLabels() {

        if (this.aktualniUzivatel != null) {
            prijem_label.setText(String.valueOf(this.aktualniUzivatel.getMesicniPrijem()));
            zustatek.setText(String.valueOf(this.aktualniUzivatel.getZustatek()));
            nahravanihodnotGrafu();
            nahravaniPolozekGrafu();


        }
    }

    /**
     * Nastaví aktuálního uživatele a zavolá metodu refreshLabels(),
     * která aktualizuje zobrazení zůstatku a měsíčního příjmu.
     * <p>
     * Tato metoda se používá při přechodu mezi okny, aby zajistila, že
     * se veškeré informace spojené s uživatelem správně zobrazí.
     */
    public void setSu(SpravceUzivatelu isu, Uzivatel iuz) {
        if (isu != null && iuz != null) {
            this.su = isu;
            this.aktualniUzivatel = iuz;
        }
        refreshLabels();
    }

}

