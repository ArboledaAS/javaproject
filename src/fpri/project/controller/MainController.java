/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpri.project.controller;

import com.jfoenix.controls.JFXSpinner;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author USER
 */
public class MainController implements Initializable {

    @FXML
    private TextField nombre_archivo;

    @FXML
    private Parent contenedor;

    @FXML
    private Label result_search;

    @FXML
    private Button btn_search;
    
    @FXML
    private ImageView viewer_image;
    
    @FXML
    private JFXSpinner loader_search;
    
    private String path_image;
    private String location_script;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void search_image() {
        try {
            String path_script = System.getProperty("user.dir") + "\\recogizer-images\\main.py ";
            System.out.println(path_script);
            result_search.setVisible(false);
            loader_search.setVisible(true);
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                Integer mn = 1;
                try {
                    Process p = Runtime.getRuntime().exec("python " + path_script + path_image);
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String ret = in.readLine();
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                            loader_search.setVisible(false);
                            result_search.setVisible(true);
                            if(ret == null){
                                result_search.setText("There isn't image that can match with your image query in dataset");
                            }else{
                                result_search.setText(ret);
                            }
                        }
                    });
                    
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return mn;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
            
        } catch (Exception ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("FingerPrint Finder 2021");
            alerta.setAlertType(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(ex.getMessage());
            alerta.showAndWait();
        }
    }

    public void leer_archivo() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Fields", "*.*"),
                new FileChooser.ExtensionFilter("JGP", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        NavigatorController navigatorController = (NavigatorController) this.contenedor.getUserData();
        File file = fileChooser.showOpenDialog(navigatorController.getStage());
        this.path_image = file.getAbsolutePath();
        this.nombre_archivo.setText(file.getAbsolutePath());
        
        Image imageSelect = new Image(file.toURI().toString());
        viewer_image.setImage(imageSelect);
        btn_search.setDisable(false);
        
    }

}
