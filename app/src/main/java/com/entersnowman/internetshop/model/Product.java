package com.entersnowman.internetshop.model;

/**
 * Created by Valentin on 24.04.2017.
 */

public class Product {
    String name;
    float price;
    String photo_url;
    boolean isAvailable;
    public  Product(){

    }
    public Product(String name, float price, String photo_url, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.photo_url = photo_url;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
