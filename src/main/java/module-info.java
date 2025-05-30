module sample.bankingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;
    requires org.testng;
    requires org.junit.platform.commons;
    opens sample.bankingapp to javafx.fxml;
    exports sample.bankingapp;
    exports Controllers;
    opens Controllers to javafx.fxml;
    exports Models;
    opens Models to javafx.fxml;
}