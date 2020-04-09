module com.carlosserrano.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.xml.bind;
    requires java.prefs;

    opens com.carlosserrano.proyectojavafx.utils to java.xml.bind; //Para que JAXB pueda ejecutarse en XMLUtil
    opens com.carlosserrano.proyectojavafx.controller to javafx.fxml;
    
    exports com.carlosserrano.proyectojavafx;
    exports com.carlosserrano.proyectojavafx.model;  //para que JAXB pueda acceder a Connection y Connection wrapper
}