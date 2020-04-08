package com.carlosserrano.proyectojavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class PrimaryController extends Controllers implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    void onLoad() {
        this.app.controller.title("CRUD - JAVAFX");
        this.app.controller.enableCon();
    }

}
