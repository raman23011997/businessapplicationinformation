/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class NewEmployeeViewController implements Initializable,ControllerClass {

    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField phoneTextField;
     @FXML private  TextField pwTextField;
     @FXML private TextField confirmpwField;
   @FXML private CheckBox admin;
    @FXML private Label errMsgLabel;
   private  Employee employee;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         errMsgLabel.setText(""); 
         
      
         
       }
           
    
        public void saveEmployeeButtonPushed(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException
    {  
        try{
        if(validpassword()){
           
              
                  employee = new Employee(firstNameTextField.getText(),lastNameTextField.getText(),
                                         phoneTextField.getText() 
                                     ,pwTextField.getText(),admin.isSelected()
            );
                      //do not show errors if creating Volunteer was successful
                    employee.insertIntoDB();  
        
                    SceneChanger sc= new SceneChanger();
                    sc.changeScenes(event, "EmployeeView.fxml", "List Of employee");
               
        }
        }catch (Exception e)
            {
                errMsgLabel.setText(e.getMessage());
            }
    
       

    }
    
    @Override
    public void preloadData2(Employee employee) {
       this.firstNameTextField.setText(employee.getFirstName());
              this.lastNameTextField.setText(employee.getLastName());
                     this.phoneTextField.setText(employee.getPhoneNumber());
    }

    @Override
    public void preloadData(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void updateVolunteer() throws IOException {
        employee.setFirstName(firstNameTextField.getText());
        employee.setLastName(lastNameTextField.getText());
        employee.setPhoneNumber(phoneTextField.getText());
        
    }
    public boolean validpassword(){
       
    {
        if (pwTextField.getText().length() < 5)
        {
            errMsgLabel.setText("Passwords must be greater than 5 characters in length");
            return false;
        }
        
        if (pwTextField.getText().equals(this.confirmpwField.getText()))
            return true;
        else
             errMsgLabel.setText("Passwords do not match");
           
            return false;
    }
    }
}
    

