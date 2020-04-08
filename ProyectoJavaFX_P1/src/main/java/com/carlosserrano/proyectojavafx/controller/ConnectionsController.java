package com.carlosserrano.proyectojavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ConnectionsController extends Controllers implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    public void back() {
        this.app.controller.changeScene(Scenes.PRIMARY);
    }

    void onLoad(){
        this.app.controller.disableCon();
        this.app.controller.title("CONEXIONES");
    }
}
