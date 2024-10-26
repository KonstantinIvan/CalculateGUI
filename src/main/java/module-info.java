module calculateMain {
    requires javafx.web;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    requires javafx.graphics;
    requires javafx.controls;
    requires org.seleniumhq.selenium.devtools_v85;

    exports com.example.calculategui;
    opens com.example.calculategui to javafx.fxml;
}