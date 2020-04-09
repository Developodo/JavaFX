package com.carlosserrano.proyectojavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AboutController extends Controllers implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void back(){
        if(this.app.controller.backScene!=null){
            this.app.controller.changeScene(this.app.controller.backScene);
        }
    }
    
}
