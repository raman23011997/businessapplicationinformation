/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class UserViewController implements Initializable {

    @FXML private Button device;
    @FXML private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void devicebuttonpushed(ActionEvent event) throws IOException{
       SceneChanger s = new SceneChanger();
      s.changeScenes(event, "SalesView.fxml", " Report Sales");
}
   public void backbuttonpushed(ActionEvent event) throws IOException{
       SceneChanger s = new SceneChanger();
      s.changeScenes(event, "LoginView.fxml", " All employees");
  
}
}
