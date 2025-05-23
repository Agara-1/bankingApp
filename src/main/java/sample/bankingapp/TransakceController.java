package sample.bankingapp;

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

public class TransakceController {
    private SpravceUzivatelu su;
    private Uzivatel aktualniUzivatel;
    // private Uzivatel prijemce;



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


    @FXML
    void poslatPenize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pridatTransakci.fxml"));
            Parent root = loader.load();
            PridatTransakciController controller = loader.getController();
            controller.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) pridatTransakciButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            loginController.setSu(su,aktualniUzivatel);
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void info() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("info.fxml"));
            Parent root = loader.load();
            InfoController infoController = loader.getController();
            infoController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) infoButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void nastaveni() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nastaveni.fxml"));
            Parent root = loader.load();
            NastaveniController nastaveniController = loader.getController();
            nastaveniController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) nastaveniButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void prevodnik() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("prevodnik.fxml"));
            Parent root = loader.load();
            PrevodnikController prevodnikController = loader.getController();
            prevodnikController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) prevodnikButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void rozpocet() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rozpocet.fxml"));
            Parent root = loader.load();
            RozpocetController rozpocetController = loader.getController();
            rozpocetController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) rozpocetButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void transakce() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("transakce.fxml"));
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
    void home() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
            Parent root = loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setSu(su, aktualniUzivatel);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    if(t.getKategorieVydaj()==null){
                        kategorie = String.valueOf(t.getKategoriePrijem());
                    }else if(t.getKategoriePrijem()==null){
                        kategorie = String.valueOf(t.getKategorieVydaj());
                    }
                    
                    String castka = t.getCastka() + " Kč";

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

    public void setSu(SpravceUzivatelu su, Uzivatel uz) {
        this.su = su;
        this.aktualniUzivatel = uz;
        nactiTransakce();
    }
}
