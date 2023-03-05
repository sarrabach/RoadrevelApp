/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cruduser.Tourist;

import com.jfoenix.controls.JFXButton;
import cruduser.util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class CrudAController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane one;
    @FXML
    private JFXButton Aadmin;
    @FXML
    private JFXButton Sadmin;
    
    @FXML
    private JFXButton Login;

    @FXML
    private JFXButton Aadmin1;

    @FXML
    private JFXButton Sadmin1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addadmin(ActionEvent event) {
     Util.loadWindow(getClass().getResource("/cruduser/Tourist/AddTourist/Add.fxml"), "Add New Place", null);

    }

    @FXML
    private void shadmins(ActionEvent event) {
             Util.loadWindow(getClass().getResource("/cruduser/Tourist/TouristList/tourist_list.fxml"), "Add New Place", null);

    }
    
    @FXML
    void shguide(ActionEvent event) {
                     Util.loadWindow(getClass().getResource("/cruduser/Guide/GuideList/Guide_list.fxml"), "Add New Place", null);
    }

    @FXML
    private void addguide(ActionEvent event) {
             Util.loadWindow(getClass().getResource("/cruduser/Guide/AddGuide/Add.fxml"), "Add New Place", null);
    }

    @FXML
    private void Login(ActionEvent event) {
            Util.loadWindow(getClass().getResource("/Login/login.fxml"), "Login", null);
    }
    
}
