/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class SalesViewController implements Initializable ,ControllerClass{
 @FXML    private DatePicker datePicker;
   
    @FXML    private TextField priceTextField;
    @FXML    private TextField EmployeeID;
    @FXML  private Label  errMsgLabel;
      @FXML  private Button  saveButton;
    private Employee employee;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void preloadData(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preloadData2(Employee employee) {
    this.datePicker.setValue(LocalDate.now());   
    
    }
    public void recordButtonPushed(ActionEvent event){
        try{
            Sales(Integer.parseInt(EmployeeID.getText()),datePicker.getValue(),Double.parseDouble(priceTextField.getText()));           
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            errMsgLabel.setText(e.getMessage());
        }
    
    }
    public void Sales(int employeeid, LocalDate Dateoftransaction, Double Price) throws SQLException {
        //validate the date is today or earlier
        if (Dateoftransaction.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Transaction cannot be in the future");
        }

        //ready to store in the database
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");

            //2. create a preparedStatement
            String sql = "INSERT INTO sales (employeeID, sellingPrice, TransactionDate) VALUES (?,?,?);";

            //3.  prepare the query
            ps = conn.prepareStatement(sql);

            //4.  convert the localdate to sql date
            Date dw = Date.valueOf(Dateoftransaction);

            //5.  bind the parameters
            ps.setInt(1, employeeid);
            ps.setDouble(2, Price);
            ps.setDate(3, dw);

            //6.  execute the update  
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }
}
