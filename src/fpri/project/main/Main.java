/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpri.project.main;

/**
 *
 * @author root
 */

import fpri.project.controller.NavigatorController;
import static fpri.project.util.Utilities.LOGGER;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    
    @FXML 
    private VBox borderPane;

    @Override
    public void start(Stage stage) {
        try {
            NavigatorController navigatorController = new NavigatorController(stage);
            navigatorController.swithUI("main_view", null);

            stage.setResizable(false);


            stage.setTitle("FingerPrint Finder 2021");
            stage.show();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
