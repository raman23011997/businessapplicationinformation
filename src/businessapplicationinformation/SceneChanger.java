/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Shanty
 */

public class SceneChanger {
    private static Employee loggedin;
    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method will change scenes and preload the next scene with a Device
     */
    public void changeScenes(ActionEvent event, String viewName, String title, Device device, ControllerClass controllerClass) throws IOException  
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //access the controller class and preloaded the volunteer data
        controllerClass = loader.getController();
        controllerClass.preloadData(device);
        
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    public void changeScenes2(ActionEvent event, String viewName, String title, Employee employee, ControllerClass controllerClass2) throws IOException  
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //access the controller class and preloaded the volunteer data
        controllerClass2 = loader.getController();
        controllerClass2.preloadData2(employee);
        
        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static Employee getLoggedin() {
        return loggedin;
    }

    public static void setLoggedin(Employee loggedin) {
        SceneChanger.loggedin = loggedin;
    }
    
}
