package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PridatTransakciController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;


    @FXML
    private Button backButton;

    @FXML
    private TextField castkaNaPoslani_TextField;

    @FXML
    private Button poslatButton;


    @FXML
    private ComboBox<KategorieTransakci> kategorieVydaje_ComboBox;

    @FXML
    void poslat() {
        int vydaj = Integer.parseInt(castkaNaPoslani_TextField.getText());
        int zustatek = aktualniUzivatel.getZustatek() - vydaj;
        aktualniUzivatel.setZustatek(zustatek);
        Transakce transakce = new Transakce();

        transakce.setKategorieVydaj(kategorieVydaje_ComboBox.getValue());
        transakce.setDatum(LocalDate.now());
        transakce.setTypTransakce(TypTransakce.VYDAJ);
        transakce.setCastka(vydaj);

        aktualniUzivatel.pridaniTransakce(transakce);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
            Parent root = loader.load();
            TransakceController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) poslatButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            transakceController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        kategorieVydaje_ComboBox.getItems().addAll(KategorieTransakci.values());
    }
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

    }
}
