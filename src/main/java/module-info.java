module sample.bankingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;
    requires org.testng;

    opens sample.bankingapp to javafx.fxml;
    exports sample.bankingapp;
}