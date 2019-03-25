package com.example.ecommerce;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    public String ProductId;
    public String Category;
    public String MainCategory;
    public String TaxTarifCode;
    public String SupplierName;
    public String WeightMeasure;
    public String WeightUnit;
    public String Description;
    public String Name;
    public String DateOfSale;
    public String ProductPicUrl;
    public String Status;
    public int Quantity;
    public String UoM;
    public String CurrencyCode;
    public int Price;
    public String Width;
    public String Depth;
    public String Height;
    public String DimUnit;

    public Product() {

    }


    protected Product(Parcel in) {
        ProductId = in.readString();
        Category = in.readString();
        MainCategory = in.readString();
        TaxTarifCode = in.readString();
        SupplierName = in.readString();
        WeightMeasure = in.readString();
        WeightUnit = in.readString();
        Description = in.readString();
        Name = in.readString();
        DateOfSale = in.readString();
        ProductPicUrl = in.readString();
        Status = in.readString();
        Quantity = in.readInt();
        UoM = in.readString();
        CurrencyCode = in.readString();
        Price = in.readInt();
        Width = in.readString();
        Depth = in.readString();
        Height = in.readString();
        DimUnit = in.readString();
    }



    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ProductId);
        dest.writeString(this.Category);
        dest.writeString(this.MainCategory);
        dest.writeString(this.TaxTarifCode);
        dest.writeString(this.SupplierName);
        dest.writeString(this.WeightMeasure);
        dest.writeString(this.WeightUnit);
        dest.writeString(this.Description);
        dest.writeString(this.Name);
        dest.writeString(this.DateOfSale);
        dest.writeString(this.ProductPicUrl);
        dest.writeString(this.Status);
        dest.writeInt(this.Quantity);
        dest.writeString(this.UoM);
        dest.writeString(this.CurrencyCode);
        dest.writeInt(this.Price);
        dest.writeString(this.Width);
        dest.writeString(this.Depth);
        dest.writeString(this.Height);
        dest.writeString(this.DimUnit);
    }
}
