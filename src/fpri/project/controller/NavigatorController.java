/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpri.project.controller;

//import fpri.project.model.Model_Mundo;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigatorController {

    private final Stage stage;

    public NavigatorController(Stage stage) {
        this.stage = stage;
    }
    
    public Stage getStage(){
        return this.stage;
    }

    public void swithUI(String fxmlName, Map params) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/fpri/project/views/" + fxmlName + ".fxml"));
            parent.setUserData(this);
            parent.getProperties().put("params", params);
            Scene ui = new Scene(parent);
            this.stage.setScene(ui);

        } catch (IOException ex) {
           Logger.getLogger(NavigatorController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
