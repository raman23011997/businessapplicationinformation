/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class LoginViewController implements Initializable {

    @FXML private TextField EmployeeIDTextField;
    @FXML private TextField pwField;
    @FXML private Label errMsgLabel;
    
    public void loginButtonPushed(ActionEvent event) throws IOException 
    {
        //query the database with the volunteerID provided, get the salt
        //and encrypted password stored in the database
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        int EmployeeNum = Integer.parseInt(EmployeeIDTextField.getText());
        
        try{
            //1.  connect to the DB
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
  
            //2.  create a query string with ? used instead of the values given by the user
            String sql = "SELECT * FROM employee WHERE employeeID = ?";
            
            //3.  prepare the statement
            ps = conn.prepareStatement(sql);
            
            //4.  bind the volunteerID to the ?
            ps.setInt(1, EmployeeNum);
            
            //5. execute the query
            resultSet = ps.executeQuery();
            
            //6.  extract the password and salt from the resultSet
            String dbPassword=null;
            byte[] salt = null;
           boolean admin = false;
            
            while (resultSet.next())
            {
                dbPassword = resultSet.getString("password");
                
                Blob blob = resultSet.getBlob("salt");
                
                //convert into a byte array
                int blobLength = (int) blob.length();
                salt = blob.getBytes(1, blobLength);
                admin = resultSet.getBoolean("admin");
           
            }
            
            //convert the password given by the user into an encryted password
            //using the salt from the database
            String userPW = PasswordGenerator.getSHA512Password(pwField.getText(), salt);
            
            SceneChanger sc = new SceneChanger();
            
            
                if(userPW.equals(dbPassword) && admin){
                
                sc.changeScenes(event, "selectView.fxml", "All Employees");
          }else if(userPW.equals(dbPassword)){
            sc.changeScenes(event, "userView.fxml", "Create Employees");
        }
                  
                  else{
                errMsgLabel.setText("The volunteerID and password do not match");
            }
                
           
        }
        catch (SQLException e)
        { 
       
            System.err.println(e.getMessage());
        }
        
    }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errMsgLabel.setText("");
    }    
    
}
