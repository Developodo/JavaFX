module com.carlosserrano.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.carlosserrano.proyectojavafx.controller to javafx.fxml;
    exports com.carlosserrano.proyectojavafx;
}