/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Raman
 */
/**
 * 
 * @class abstract device that have all variable that are defined
 */
public  class Device {
    private String model,make,operatingSystem;
    private int manufacturingYear;
    protected double screenSize,price;
    private File imageFile;
    private int id;
/**
 * a constructor that is used to pass all the parameters and then setting their values
 * @param model
 * @param make
 * @param manufacturingYear
 * @param screenSize
 * @param operatingSystem
 * @param price 
 */
    public Device(String model, String make, int manufacturingYear, double screenSize,
            String operatingSystem,double price) {
        setModel(model);
        setOperatingSystem(operatingSystem);
      setManufacturingYear( manufacturingYear);
        setScreenSize(screenSize);
        setMake( make);
        setPrice(price); 
        setImageFile(new File("./src/b.jpg"));
    }
    /**
     * all the get method to to return particular type of data,,like File ,string or nthng
     * @return 
     */
   public File getImageFile() {
        return imageFile;
    }
/**
 * set the image name
 * @param imageFile 
 */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    /**
     * ser model value and throw exception if empty
     * @param model 
     */
    public void setModel(String model) {
        if(model.isEmpty()){
           throw new IllegalArgumentException("Model Should not be Empty");
        }else{
            this.model=model;
        }
    }

    public double getPrice() {
        return price;
    }
/**
     * set price value and throw exception if less than 0
     * @param Price
     */
    public void setPrice(double price) {
        if(price<0){
           throw new IllegalArgumentException("Price Should not be Less than zero");
        }else{
         
            this.price = price;
        }
        
    }
/**
     * set make value and throw exception if empty
     * @param make 
     */
    public void setMake(String make) {
        if(make.isEmpty()){
            throw new IllegalArgumentException("make Should not be Empty");
        }else{
            this.make=make;
        }
    }
/**
     * set operatingSystem value and throw exception if empty
     * @param operatingSystem 
     */
    public void setOperatingSystem(String operatingSystem) {
        if(operatingSystem==" "){
            throw new IllegalArgumentException("make Should not be Empty");
        }else{
            this.operatingSystem=operatingSystem;
        }
    }
/**
 * set manufacturingYear otherwise throw exception if less than 19000 greater than current year
 * @param manufacturingYear 
 */
    public void setManufacturingYear(int manufacturingYear) {
        if(manufacturingYear<1900 && manufacturingYear<LocalDate.now().getYear()){
           throw new IllegalArgumentException("Manufacturing Year Should not be Less than 1900 and cant be greater than Current Year");
        }else{

              this.manufacturingYear = manufacturingYear;
        }
      
    }
/**
 * throw exception if less than 0
 * @param screenSize 
 */
    public void setScreenSize(double screenSize) {
       if(screenSize<0){
          throw  new IllegalArgumentException("Screen Size Should not be Less than zero");
        }else{
            this.screenSize=screenSize;
        }
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }
public int getId() {
        return id;
    }

    public double getScreenSize() {
        return screenSize;
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
            String sql = "INSERT INTO deviceinfo(make, model, os, year,screensize,price )"
                    + "VALUES (?,?,?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
            
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, operatingSystem);
            preparedStatement.setInt(4, manufacturingYear);
            preparedStatement.setDouble(5, screenSize);
            preparedStatement.setDouble(6, price);
          
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
     public void updateVolunteerInDB() throws SQLException
    {
        
        Connection conn = null;
                 
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/device?useSSL=false", "root", "ramanlove@1997");
  
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE  deviceinfo SET make = ?, model = ?, os=?, year = ?, screensize = ?,price = ?"
                    + "WHERE deviceid = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
            //4.  convert the birthday into a date object
           
            
            //5. bind the parameters
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, operatingSystem);
            preparedStatement.setInt(4, manufacturingYear);
            preparedStatement.setDouble(5, screenSize);
            preparedStatement.setDouble(6, price);
            preparedStatement.setInt(7, id);
            
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
