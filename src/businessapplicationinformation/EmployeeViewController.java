/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class EmployeeViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<Employee> EmployeeTable;
    @FXML private TableColumn<Employee, Integer> IDColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> phoneColumn;
   @FXML Button editButton;
    private Employee employee;
   public void DeviceSelected(){
        editButton.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         editButton.setDisable(true);
       IDColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
    try{
            loadVolunteers();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        } 
    }    
 
    
    public void loadVolunteers() throws SQLException
    {
        ObservableList<Employee> employee = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
     //2.  create a statement object
            statement = conn.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM employee");
            
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
                Employee newEmployee = new Employee(resultSet.getString("firstName"),
                                                       resultSet.getString("lastName"),
                                                       resultSet.getString("phoneNumber"),
                 resultSet.getString("password"),resultSet.getBoolean("admin"));
             
                                                       newEmployee.setID(resultSet.getInt("employeeID"));
              
                
                employee.add(newEmployee);
            }
            
            EmployeeTable.getItems().addAll(employee);
            
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
    public void createButtonPushed(ActionEvent event) throws IOException{
         SceneChanger sc= new SceneChanger();

        sc.changeScenes(event, "NewEmployeeView.fxml", "Add new employee");
               
    }
    public void editButtonPushed(ActionEvent event) throws IOException{
        SceneChanger sc= new SceneChanger();
                 Employee employee=this.EmployeeTable.getSelectionModel().getSelectedItem();
                 NewEmployeeViewController a= new NewEmployeeViewController();
        sc.changeScenes2(event, "NewEmployeeView.fxml", "edit employee", employee, a);
    }
    
}
