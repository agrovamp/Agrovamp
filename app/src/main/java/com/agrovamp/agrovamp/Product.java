package com.agrovamp.agrovamp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nishat Sayyed on 10-04-2018.
 */

public class Product implements Parcelable {
    private String name;
    private int price;
    private String description;
    private String imageName;

    public Product(String name, int price, String imageName) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readInt();
        description = in.readString();
        imageName = in.readString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeString(description);
        parcel.writeString(imageName);
    }
}
