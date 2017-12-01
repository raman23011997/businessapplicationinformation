/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

/**
 *
 * @author Shanty
 */
public class mobilephone extends device {
    /**
     * mobile phone passes following parameters and then set their values
     * @param model
     * @param make
     * @param manufacturingYear
     * @param screenSize
     * @param operatingSystem
     * @param price 
     */
    public mobilephone(String model, String make, int manufacturingYear, double screenSize, String operatingSystem,double price) {
        super(model, make, manufacturingYear, screenSize, operatingSystem,price);
        
    }
 /**
  * override method which will throw exception if size is less than 0 or greater than 6
  * @param screenSize 
  */
    @Override
    public void setScreenSize(double screenSize) {
        if(screenSize<6 && screenSize>0){
            this.screenSize=screenSize;
        }else{
           throw  new IllegalArgumentException("Screen Size Should be less than 6 Inches and greater than 0");
        }
    }
 
    
}
