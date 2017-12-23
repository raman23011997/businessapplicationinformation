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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FinalViewSaleController implements Initializable {
 @FXML private TableView<Sales> salesTable;
    @FXML private TableColumn<Sales, Integer> IDColumn;
    @FXML private TableColumn<Sales, Double> priceColumn;
    @FXML private TableColumn<Sales,  Double> sellColumn;
    @FXML private TableColumn<Sales, Date> dateColumn;
   @FXML Button backButton;
    private Sales sale;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IDColumn.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("ID"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Sales, Double>("sellingPrice"));
        sellColumn.setCellValueFactory(new PropertyValueFactory<Sales, Double>("cost"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Sales, Date>("date"));
    try{
            loadSales();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        } 
        
 
    
    }
public void loadSales() throws SQLException
    {
        ObservableList<Sales> sales = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
     //2.  create a statement object
            statement = conn.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM sales");
            
            Date dw = Date.valueOf("date");
            //4.  create volunteer objects from each record
            while (resultSet.next())
            {
                sale = new Sales(resultSet.getInt("ID"),
                                                       resultSet.getDouble("sellingprice"),
                                                       resultSet.getDouble("cost"),
                      resultSet.getDate("TransactionDate"));
             
                         
              
                
                sales.add(sale);
            }
            
            salesTable.getItems().addAll(sales);
            
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
    
}
