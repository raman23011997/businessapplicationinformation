/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.sql.SQLException;

/**
 *
 * @author Shanty
 */
public class Businessapplicationinformation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Device Device = new Device( "djb","din",1996,5.0,"Ios",426.3);
        Device.insertIntoDB();
    }
    
}
