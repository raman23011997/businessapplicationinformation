/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Shanty
 */
public class Employee {
     private String firstName, lastName, phoneNumber;
     private int ID;
     private String Password;
       private byte[] salt;
       private boolean admin;

    public Employee(String firstName, String lastName, String phoneNumber ,String password,boolean admin) throws NoSuchAlgorithmException{
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        salt = PasswordGenerator.getSalt();
        this.Password = PasswordGenerator.getSHA512Password(password, salt);
           this.admin=admin;
        
    }

    public int getID() {
        return ID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setID(int ID) {
     if (ID >= 0)
            this.ID = ID;
        else
            throw new IllegalArgumentException("VolunteerID must be >= 0");
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * area code    city    house 
     * NXX          -XXX    -XXXX
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{4}"))
            this.phoneNumber = phoneNumber;
        else
            throw new IllegalArgumentException("Phone numbers must be in the pattern NXX-XXX-XXXX");
    }
public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try
        {
            //1. Connect to the database
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
  
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO employee (firstName, lastName, phoneNumber,password,salt,admin)"
                    + "VALUES (?,?,?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
      
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber); 
             preparedStatement.setString(4, Password); 
              preparedStatement.setBlob(5, new javax.sql.rowset.serial.SerialBlob(salt)); 
               preparedStatement.setBoolean(6,admin); 
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
    }
   public void updateEmployeeInDB() throws SQLException
    {   
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
  
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE employee SET firstName = ?, lastName = ?, phoneNumber=?"
                    + "WHERE employeeid = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
            //4.  convert the birthday into a date object
          
            
            //5. bind the parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setInt(4, ID);
            
            //6. run the command on the SQL server
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (preparedStatement != null)
                preparedStatement.close();
        }
        
    } 

}
