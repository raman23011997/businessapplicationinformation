/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.util.ArrayList;

/**
 *
 * @author Shanty
 */
public class inventory {
    private ArrayList<mobilephone>  mobilephonearray;

    public inventory() {
        this.mobilephonearray = new ArrayList<>();
    }

    public ArrayList<mobilephone> getMobilephonearray() {
        return mobilephonearray;
    }

    public void add(mobilephone mobilephone) {
        this.mobilephonearray.add(mobilephone);
    }
    
}
