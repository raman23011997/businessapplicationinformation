/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class InventoryViewController implements Initializable {
/**
 * declare all the variables 
 */
    public ObservableList<device> deviceList = FXCollections.observableArrayList();
    @FXML
    private TableView<device> deviceTable;
 private double r;
    @FXML
    private TableColumn<device, String> makeColumn;
    @FXML
    private TableColumn<device, String> modelColumn;
    @FXML
    private TableColumn<device, String> operatinggsystemColumn;

    @FXML
    private Label totalPriceLabel;
        @FXML
    private Label totalInventoryLabel;
    @FXML
    private TableColumn<device, Integer> manufacturingYearColumn;
    @FXML
    private TableColumn<device, Double> screenSizeColumn;
    @FXML
    private TableColumn<device, Double> priceColumn;
/**
 * this will set the value of 
 * @param url
 * @param rb 
 */
    public void initialize(URL url, ResourceBundle rb) {
        makeColumn.setCellValueFactory(new PropertyValueFactory<device, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<device, String>("model"));
        operatinggsystemColumn.setCellValueFactory(new PropertyValueFactory<device, String>("operatingSystem"));
        manufacturingYearColumn.setCellValueFactory(new PropertyValueFactory<device, Integer>("manufacturingYear"));
        screenSizeColumn.setCellValueFactory(new PropertyValueFactory<device, Double>("screenSize"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<device, Double>("price"));
/**
 * this will add the mobilephone to the deviceList 
 */
        deviceList.add(new mobilephone("Apple", "x", 2016, 5.00, "IOS", 1500));
        deviceList.add(new mobilephone("Samsung", "S8", 2015, 5.00, "Android", 1000));
        

//load dummy data
        deviceTable.setItems(deviceList);
        
  //  this will set the total price of the phone and total size of inventory                                        
  for(int i=0;i<deviceList.size();i++){
     
       r +=deviceList.get(i).getPrice();
      this.totalPriceLabel.setText("$ "+r);
      this.totalInventoryLabel.setText(""+deviceList.size());
  }
                    
    }

       
    

    public void createNewBookButtonPushed(ActionEvent event) throws IOException
    {
 
         //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addDeviceView.fxml"));
        Parent parent = loader.load();
        Scene newBookScene = new Scene(parent);
        
        //access the controller of the newBookScene and send over
        //the current list of Books
        AddDeviceViewController controller = loader.getController();
       
        
        //Get the current "stage" (aka window)         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = ((Stage)((Button) event.getSource()).getScene().getWindow());
        //change the scene to the new scene
        stage.setTitle("Create new Device");
        stage.setScene(newBookScene);
        stage.show();
    }//method to load phones to ObservableList
    public ObservableList LoadPhones(){
        return deviceList;
    }

}
/**
 * Initializes the controller class.
 */
