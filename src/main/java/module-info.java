module sample.bankingapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens sample.bankingapp to javafx.fxml;
    exports sample.bankingapp;
}