package sample.bankingapp;

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

import java.io.IOException;

public class UpravitLimitController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;

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

    @FXML
    void potvrdit() {

        String castka = castkaNaPoslani_TextField.getText();
//        limitDoprava_Label.setText(String.valueOf(zakladniLimit));
//        limitBezny_Label.setText(String.valueOf(zakladniLimit));
//        limitZabava_Label.setText(String.valueOf(zakladniLimit));
//        limitOstatni_Label.setText(String.valueOf(zakladniLimit));

        if (kategorieVydaje_ComboBox == null || castka.isEmpty()) {
            chybaText.setText("Vyber kategorii a zadej částku.");
            return;
        }

        for (Transakce t : aktualniUzivatel.getSeznamTransakci()) {
            if (t.getTypTransakce() == TypTransakce.VYDAJ && t.getKategorieVydaj() != null) {
                switch (t.getKategorieVydaj()) {
                    case JIDLO:
                        aktualniUzivatel.setLimitJidlo(Integer.parseInt(castka));
                        break;
                    case DOPRAVA:
                        aktualniUzivatel.setLimitDoprava(Integer.parseInt(castka));
                        break;
                    case ZABAVA:
                        aktualniUzivatel.setLimitZabava(Integer.parseInt(castka));
                        break;
                    case BEZNE_VYDAJE:
                        aktualniUzivatel.setLimitBezny(Integer.parseInt(castka));
                        break;
                    case OSTATNI:
                        aktualniUzivatel.setLimitOstatni(Integer.parseInt(castka));
                        break;
                }

            }
        }
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

    @FXML
    void initialize() {
        kategorieVydaje_ComboBox.getItems().addAll(KategorieTransakci.values());

    }

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;

    }

}
