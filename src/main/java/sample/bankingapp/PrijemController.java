 package sample.bankingapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrijemController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    @FXML
    private Text chybaText;

    @FXML
    private TextField mesicniPrijem_TextField;

    @FXML
    private Button pridatButton;

    @FXML
    private TextField typPrijmu_TextField;

    @FXML
    void Prijem(ActionEvent event) {

    }
    @FXML
    void initialize() {

    }
    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
    }
}
