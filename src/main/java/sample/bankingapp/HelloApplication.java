package sample.bankingapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *Třída HelloAplication spouští celou aplikaci.
 */
public class HelloApplication extends Application {
    /**
     * * Metoda  otevře první okno aplikace a to registrační obrazovku (SignUp).
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sample/bankingapp/signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load() );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}