package sample.bankingapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PridatTransakciController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField castkaNaPoslani_TextField;

    @FXML
    private Text chybaText;

    @FXML
    private TextField cisloUctuNaPoslani_TextField;

    @FXML
    private Button poslatButton;

    @FXML
    void poslat(ActionEvent event) {

    }
    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
            Parent root = loader.load();
            TransakceController transakceController = loader.getController();
            //transakceController.setSu(su, aktualniUzivatel);
            //Stage stage = (Stage) transakceButton.getScene().getWindow();
            //stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {

    }

}
