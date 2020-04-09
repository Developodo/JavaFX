package com.carlosserrano.proyectojavafx.controller;

import com.carlosserrano.proyectojavafx.model.Connection;
import com.carlosserrano.proyectojavafx.utils.Dialog;
import com.carlosserrano.proyectojavafx.utils.MapEntry;
import com.carlosserrano.proyectojavafx.utils.PreferencesUtil;
import com.carlosserrano.proyectojavafx.utils.XMLUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class ConnectionsController extends Controllers implements Initializable {

    //OBSERVABLE <--------
    public ObservableList<Connection> conns;
    
    @FXML
    private TableView<Connection> connTable;
    @FXML
    private TableColumn<Connection, String> nameConn;

    @FXML
    private Label L_nameConn;
    @FXML
    public Label L_type;
    @FXML
    private Label L_server;
    @FXML
    private Label L_user;
    @FXML
    private Label L_pass;
    
    @FXML
    private HBox tools;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.conns=FXCollections.observableArrayList();
        this.conns.addAll(XMLUtil.loadDataXML());
        
        nameConn.setCellValueFactory(cellData -> {
            if(cellData.getValue().equals(app.controller.currentConnection)){
                return new SimpleObjectProperty<>(cellData.getValue().getName()+" ( C )");
            }else{
                return cellData.getValue().getN();
            }
            
        });

        showConnDetails(null);

        connTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showConnDetails(newValue)
        );
        //Add observable
        connTable.setItems(conns);
    }

    private void showConnDetails(Connection c) {
        if (c != null) {
            tools.setDisable(false);
            L_nameConn.setText(c.getName());
            L_type.setText(c.getType());
            L_server.setText(c.getServer());
            L_user.setText(c.getUserName());
            L_pass.setText(c.getPassword());

        } else {
            tools.setDisable(true);
            resetForm();
        }
    }

    private void resetForm() {
        L_nameConn.setText("");
        L_type.setText("");
        L_server.setText("");
        L_user.setText("");
        L_pass.setText("");
    }

    @FXML
    public void back() {
        this.app.controller.changeScene(Scenes.PRIMARY);
    }

    void onLoad() {
        this.app.controller.disableCon();
        this.app.controller.title("CONEXIONES");
    }

    @FXML
    private void handleNewConnection() {
        editConnectionController cc = (editConnectionController) app.controller.openModal(Scenes.EDIT, "Nueva Conexión",this,null);
    }
    
    @FXML
    private void handleConConnection(){
        Connection c = connTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            Dialog.showWarning("Aviso", "No hay conexión seleccionada", "Seleccione una conexión antes de pulsar connectar");
        } else {
            app.controller.currentConnection=c;
            app.controller.title("CONEXIONES");
            PreferencesUtil.setPreference(c.getName());
            conns.add(new Connection()); //refresh gui
        }
    }

    @FXML
    private void handleEditConnection() {
        Connection c = connTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            Dialog.showWarning("Aviso", "No hay conexión para editar", "Seleccione una conexión antes de pulsar editar");
        } else {
            editConnectionController cc = (editConnectionController) app.controller.openModal(Scenes.EDIT, "Editando Conexión",this,c);
        }
    }
    
    @FXML
    private void handleRemoveConnection() {
        Connection c = connTable.getSelectionModel().getSelectedItem();
        if (c == null) {
            Dialog.showWarning("Aviso", "No hay conexión para borrar", "Seleccione una conexión antes de pulsar borrar");
        } else {
            if(c.equals(app.controller.currentConnection)){
                app.controller.currentConnection=null;
                PreferencesUtil.setPreference(null);
                app.controller.title("CONEXIONES");
            }
            conns.remove(c);
            XMLUtil.writeDataXML(conns);
        }
    }

    @Override
    void doOnCloseModal(Object response) {
        if (response != null) {
            MapEntry<Connection, Boolean> r = (MapEntry<Connection,Boolean>) response;
            if(r.getValue()){  //new conn to be added
                conns.add(r.getKey());
            }
            showConnDetails(r.getKey());  //update GUI
            XMLUtil.writeDataXML(conns);
            //SAVE
        }
        
    }
}
