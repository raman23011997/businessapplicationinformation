/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessapplicationinformation;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Shanty
 */
public class Sales {
    private int ID;
    private double sellingPrice,cost ;
    private Date date;
    public Sales(int id ,double sellingPrice,double cost,Date date ){
        setID(id);
        setSellingPrice(sellingPrice);
        setCost(cost);
        setDate(date);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
