module com.smartscale.recipe_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    requires phidget22;
    requires jdk.compiler;

    opens com.smartscale.recipe_app to javafx.fxml;
    exports com.smartscale.recipe_app;
}