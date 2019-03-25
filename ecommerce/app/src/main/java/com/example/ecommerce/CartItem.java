package com.example.ecommerce;

import android.util.Log;

public class CartItem {
    String ProductName;
    int ProductPrice;
    int ProductQuantity;
    int TotalPrice;

    public CartItem (String prodname, int quantity, int indiPrice) {
        this.ProductName = prodname;
        this.ProductPrice = indiPrice;
        this.ProductQuantity = quantity;
        TotalPrice = ProductPrice*ProductQuantity;
    }

    public void increaseQuantity (int quantity) {
        this.ProductQuantity = this.ProductQuantity + quantity;
        this.TotalPrice = this.ProductQuantity*this.ProductPrice;
        Log.d("CheckCartIncrement" , String.valueOf(this.ProductQuantity));
    }



    @Override
    public String toString() {
        return ProductName + ": " + TotalPrice;
    }
}
