package sample.bankingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup group1;

    @FXML
    void initialize() {
        assert group1 != null : "fx:id=\"group1\" was not injected: check your FXML file 'info.fxml'.";

    }

}
