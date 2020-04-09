package com.carlosserrano.proyectojavafx.controller;

import com.carlosserrano.proyectojavafx.model.Connection;
import com.carlosserrano.proyectojavafx.model.ConnectionsType;
import com.carlosserrano.proyectojavafx.utils.Dialog;
import com.carlosserrano.proyectojavafx.utils.MapEntry;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editConnectionController extends ModalControllers implements Initializable {

    private Connection c;

    private boolean creating;

    @FXML
    private TextField L_name;
    @FXML
    private TextField L_server;
    @FXML
    private TextField L_user;
    @FXML
    private TextField L_pass;

    @FXML
    public ChoiceBox<String> type;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (type != null) {
            for (ConnectionsType _type : Connection.connectionTypes.values()) {
                type.getItems().add(_type.getType());
            }
        }
    }

    public void setParams(Object p) {
        Connection c = (Connection) p;
        if (c == null) {
            this.c = new Connection();
            resetAll();
            creating = true;
        } else {
            this.c = c;
            L_name.setText(c.getName());
            L_server.setText(c.getServer());
            L_user.setText(c.getUserName());
            L_pass.setText(c.getPassword());
            type.getSelectionModel().select(c.getType());
            L_name.setEditable(false);
            creating = false;
        }
    }

    public void setModalStage(Stage s) {
        this.stage = s;
    }

    @FXML
    public void handleOK() {
        if (isValid()) {
            if (c == null) {
                this.c = new Connection();
                this.creating = true;
            }
            this.c.setName(L_name.getText());
            this.c.setType(type.getSelectionModel().getSelectedItem());
            this.c.setServer(L_server.getText());
            this.c.setUserName(L_user.getText());
            this.c.setPassword(L_pass.getText());

            if (this.parentController != null) {
                MapEntry<Connection, Boolean> response = new MapEntry<>(this.c, creating);
                this.parentController.doOnCloseModal(response);
            }

            this.stage.close();
        }
    }

    @FXML
    public void handleCancel() {
        System.out.println(this.parentController);
        if (this.parentController != null) {
            this.parentController.doOnCloseModal(null);
        }
        this.stage.close();
    }

    private boolean isValid() {
        String error = "";

        if (L_name.getText() == null || L_name.getText().length() == 0) {
            error += " Nombre de conexión no válido \n";
        } else {
            if (creating) {
                //search a conn with same name
                Connection tmp = new Connection(L_name.getText());
                ConnectionsController cc = (ConnectionsController) parentController;
                if (cc.conns.contains(tmp)) {
                    error += " Nombre de conexión no válido, ya existe una conexión con el mismo nombre\n";
                }
            }
        }

        if (error.length() == 0) {
            return true;
        } else {
            Dialog.showError("Error", "Corrija los errores", error);
            return false;
        }
    }

    private void resetAll() {
        type.getSelectionModel().select(1);
        L_name.setText("");
        L_server.setText("");
        L_user.setText("");
        L_pass.setText("");
    }

}
