package com.example.devarajakhil.inventory;

/**
 * Created by Devaraj Akhil on 08-02-2018.
 */

public class DataObject {
    public String name;
    public String price;
    public String quantity;

    //public DataObject(){}

    /*public DataObject(String name,int price,int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }*/
    public String getName(){return name;}
    public String getPrice(){return price;}
    public String getQuantity(){return quantity;}
   // public void setName(String name){this.name=name;}
  //  public void setPrice(int price){this.price=price;}
   // public void setQuantity(int quantity){this.quantity=quantity;}
}
