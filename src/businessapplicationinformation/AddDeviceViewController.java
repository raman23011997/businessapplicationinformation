/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Shanty
 */
public class AddDeviceViewController implements Initializable,ControllerClass {
    
@FXML private TextField maketextfield;
@FXML private TextField modeltextfield;
@FXML private TextField operatindsystemtextfield;
@FXML private TextField screensizetextfield;
@FXML private TextField yeartextfield;
@FXML private TextField pricefield;
@FXML private Button addButton;
 @FXML private Label errorMessageLabel;
 @FXML private ImageView imageView;
 private File imageFile;
private FileChooser choosefile;
private Device device;
    /**
     * Initializes the controller class.
     */
public void addButtonPushed(ActionEvent event) throws Exception
{
      try{ 
          if(device!=null){
              
              device.updateVolunteerInDB();
          }
              
          else {
        device= new Device(maketextfield.getText(),modeltextfield.getText(),
        Integer.parseInt(yeartextfield.getText()),Double.parseDouble(screensizetextfield.getText()),operatindsystemtextfield.getText()
        ,Double.parseDouble(pricefield.getText()));
    
    device.insertIntoDB();
          }
         //load a new scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InventoryView.fxml"));
        Parent parent = loader.load();
        Scene newBookScene = new Scene(parent);
        
        //access the controller of the newBookScene and send over
        //the current list of Books
        InventoryViewController controller = loader.getController();
       
  
        //Get the current "stage" (aka window)         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = ((Stage)((Button) event.getSource()).getScene().getWindow());
        //change the scene to the new scene
        stage.setTitle("Devices");
        stage.setScene(newBookScene);
        stage.show();
       
              
                  
    }
    catch (IllegalArgumentException e)
            {
                this.errorMessageLabel.setVisible(true);
                this.errorMessageLabel.setText(e.getMessage());
              
            }
    }
    public void chooseimageButtonPushed(ActionEvent event) 
    {
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
       choosefile = new FileChooser();
        choosefile.setTitle("Choose Image");
        //This line gets the Stage information
      this.imageFile=choosefile.showOpenDialog(stage);
       try{
         // make the temperory buffered image   
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            this.errorMessageLabel.setVisible(false);
            
               try{
            imageFile = new File("./src/b.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            // set the final image
            imageView.setImage(image);
            
        }// catch exception
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }    

    @Override
    public void preloadData(Device device) {
    this.device=device;
    this.maketextfield.setText(device.getMake());
    this.modeltextfield.setText(device.getModel());
    this.operatindsystemtextfield.setText(device.getOperatingSystem());
    double a= device.getScreenSize();
    String b= Double.toString(a);
    this.screensizetextfield.setText(b);
     int intnew= device.getManufacturingYear();
    String c= Integer.toString(intnew);
   this.yeartextfield.setText(c);
   double newdouble= device.getPrice();
    String d= Double.toString(newdouble);
    this.pricefield.setText(d);
    
    
    }

    @Override
    public void preloadData2(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
          
    