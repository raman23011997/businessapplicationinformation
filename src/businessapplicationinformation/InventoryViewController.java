/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    @FXML Button editButton;
    public ObservableList<Device> deviceList = FXCollections.observableArrayList();
    @FXML
    private TableView<Device> deviceTable;
 private double r;
    @FXML
    private TableColumn<Device, String> makeColumn;
    @FXML
    private TableColumn<Device, String> modelColumn;
    @FXML
    private TableColumn<Device, String> operatinggsystemColumn;

    @FXML
    private Label totalPriceLabel;
        @FXML
    private Label totalInventoryLabel;
    @FXML
    private TableColumn<Device, Integer> manufacturingYearColumn;
    @FXML
    private TableColumn<Device, Double> screenSizeColumn;
    @FXML
    private TableColumn<Device, Double> priceColumn;
/**
 * this will set the value of 
 * @param url
 * @param rb 
 */
    public void editbuttonpushed(ActionEvent event) throws IOException{
        SceneChanger na = new SceneChanger();
        Device device=this.deviceTable.getSelectionModel().getSelectedItem();
        AddDeviceViewController as= new AddDeviceViewController();
        na.changeScenes(event,"addDeviceView.fxml", "Edit Volunteer", device, as);
    }
    public void DeviceSelected(){
        editButton.setDisable(false);
    }
    public void initialize(URL url, ResourceBundle rb) {
       editButton.setDisable(true);
        makeColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("model"));
        operatinggsystemColumn.setCellValueFactory(new PropertyValueFactory<Device, String>("operatingSystem"));
        manufacturingYearColumn.setCellValueFactory(new PropertyValueFactory<Device, Integer>("manufacturingYear"));
        screenSizeColumn.setCellValueFactory(new PropertyValueFactory<Device, Double>("screenSize"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Device, Double>("price"));
/**
 * this will add the mobilephone to the deviceList 
 */
try{
            loadDevices();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
        
 public void loadDevices() throws SQLException
    {
        ObservableList<Device> Device = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
            //2.  create a statement object
            statement = conn.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM deviceinfo");
            
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
                Device newdevice = new Device(resultSet.getString("make"),
                                                       resultSet.getString("model"),
                                                       resultSet.getInt("year"),
                                                       resultSet.getDouble("screensize"),
                                                       resultSet.getString("os"),
                                                       resultSet.getDouble("price"));
        
             
                
                   Device.add(newdevice);
            }
            
            deviceTable.getItems().addAll(Device);
     resultSet = statement.executeQuery("SELECT sum(price) FROM deviceinfo");
     resultSet.next();
     String sum = resultSet.getString(1);
     BigDecimal a = new BigDecimal(sum);
BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    totalPriceLabel.setText(roundOff.toString());
    resultSet = statement.executeQuery("SELECT count(price) FROM deviceinfo");
     resultSet.next();
     String count = resultSet.getString(1);
    totalInventoryLabel.setText(count);
    
        } catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
    }

//
                    
    
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
