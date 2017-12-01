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
    
    public mobilephone(String model, String make, int manufacturingYear, double screenSize, String operatingSystem,double price) {
        super(model, make, manufacturingYear, screenSize, operatingSystem,price);
        
    }
 
    @Override
    public void setScreenSize(double screenSize) {
        if(screenSize<6 && screenSize>0){
            this.screenSize=screenSize;
        }else{
           throw  new IllegalArgumentException("Screen Size Should be less than 6 Inches and greater than 0");
        }
    }
 
    
}
