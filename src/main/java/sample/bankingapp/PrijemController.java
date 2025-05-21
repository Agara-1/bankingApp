package sample.bankingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.crypto.KeyAgreement;
import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;

public class PrijemController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;

    public PrijemController(SpravceUzivatelu su) {
        this.su = su;
        this.aktualniUzivatel = su.getAktualniUzivatel();
    }


    @FXML
    private Text chybaText;

    @FXML
    private TextField mesicniPrijem_TextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField typPrijmu_TextField;


    @FXML
    private Label typTransakcce_label;
    @FXML
    void prijem() {
        int prijem = Integer.parseInt(mesicniPrijem_TextField.getText());
        int zustatek = aktualniUzivatel.getZustatek() + prijem;
        aktualniUzivatel.setMesicniPrijem(prijem);
        aktualniUzivatel.setZustatek(zustatek);
        Transakce transakce = new Transakce();
        aktualniUzivatel.pridaniTransakce(transakce);
        transakce.setKategorie(typPrijmu_TextField.getText());
        transakce.setDatum(LocalDate.now());
        transakce.setTypTransakce(TypTransakce.PRIJEM);
        transakce.setCastka(prijem);

        aktualniUzivatel.pridaniTransakce(transakce);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root = loader.load();
            HomePageController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) pridatButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void vojtaPrijem() {

        int a = 5;
    }

    @FXML
    void initialize() {

    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

    }
}
