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

    @FXML
    void poslat() {

        int vydaj = Integer.parseInt(castkaNaPoslani_TextField.getText());

        if (!cisloUctuNaPoslani_TextField.getText().matches("^\\d{4} \\d{4} \\d{4}$")) {
            chybaText.setText("Číslo účtu musí být ve formátu XXXX XXXX XXXX.");
            return;
        }
        if (vydaj <= 0) {
            chybaText.setText("Částka musí být kladné číslo.");
            return;
        }

        if (vydaj > aktualniUzivatel.getZustatek()) {
            chybaText.setText("Nedostatek prostředků na účtu.");
            return;
        }
        if (castkaNaPoslani_TextField.getText().isEmpty() || cisloUctuNaPoslani_TextField.getText().isEmpty() || kategorieVydaje_ComboBox == null) {
            chybaText.setText("Musíte zadat všechny hodnoty.");
            return;
        }
        try {
            vydaj = Integer.parseInt(castkaNaPoslani_TextField.getText());
        } catch (NumberFormatException e) {
            chybaText.setText("Částka musí být celé číslo.");
            return;
        }
            int zustatek = aktualniUzivatel.getZustatek() - vydaj;

            aktualniUzivatel.setZustatek(zustatek);
            Transakce transakce = new Transakce();


            transakce.setKategorieVydaj(kategorieVydaje_ComboBox.getValue());
            transakce.setDatum(LocalDate.now());
            transakce.setTypTransakce(TypTransakce.VYDAJ);
            transakce.setCastka(vydaj);

            aktualniUzivatel.pridaniTransakce(transakce);

            su.serializaceUzivatelu();
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
