/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosserrano.proyectojavafx.controller;

import com.carlosserrano.proyectojavafx.App;
import com.carlosserrano.proyectojavafx.utils.MapEntry;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;

public class AppController extends Controllers{
    //scene actual cargada
    public Scenes backScene;
    public Scenes currentScene;
      
    /**
     * Recibe la url de una archivo FXML (de la carpeta resources) y devuelve su contenedor y controlador
     * @param fxml url del archivo 
     * @return @see(MapEntry) de un contenedor y su controlador si lo tuviera
     * @throws IOException en caso de error de lectura
     */
    public static MapEntry<Parent,Controllers> loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent p=fxmlLoader.load();
        Controllers c=fxmlLoader.getController();
        MapEntry<Parent, Controllers> result=new MapEntry<>(p,c);
        return result;
    }
   
    /**
     * Carga en el Layout de la app principal la escena que se le pase en la 
     * zona central. (requisito: el layout principal debe ser borderpane).
     * Le pasa automaticamente al controlador de la escena la referncia a la
     * clase principal para poder tener acceso a su controlador.
     * @param scene La escena a cargar @see(Scenes)
     */
    public void changeScene(Scenes scene){
        try {
            MapEntry<Parent, Controllers> m=loadFXML(scene.getUrl());
            this.app.rootLayout.setCenter(m.getKey());
            if(m.getValue()!=null)
                 m.getValue().setMainApp(this.app);
            if(backScene!=currentScene){
                backScene=currentScene;
            }
            this.currentScene=scene;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     /**
     * No se usa en este proyecto, dado que nunca se cambiará el rootLayout.
     * Se deja como documentación
     * @param fxml
     * @throws IOException 
     */
     private void setRoot(Scenes scene) throws IOException {
        this.backScene=null;
        this.currentScene=null;
        MapEntry<Parent, Controllers> m=loadFXML(scene.getUrl());
        this.app.scene.setRoot(m.getKey());
        this.app.controller=(AppController)m.getValue();
    }
    
    @FXML
    private MenuItem con;
     
    @FXML
    public void connectionsMenu(){
        changeScene(Scenes.CONN);
    }
    @FXML
    public void AboutPage(){
        changeScene(Scenes.ABOUT);
    }
    @FXML
    public void closeApp(){
        System.exit(0);
    }
    @FXML
    public void enableCon(){
        if(con!=null)
        con.setDisable(false);
    }
    @FXML
    public void disableCon(){
        if(con!=null)
        con.setDisable(true);
    }
    @FXML
    public void title(String txt){
        this.app.mainStage.setTitle(txt);
    }

}
