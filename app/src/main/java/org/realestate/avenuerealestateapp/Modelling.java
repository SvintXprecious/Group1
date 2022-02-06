package org.realestate.avenuerealestateapp;
//Component by Khumbo Zimba(BIT-030-18)

public class Modelling {
    String ImageUrl;
    String ProductNAme;
    String location;
    String price;
    String description;

    public Modelling(String imageUrl, String productNAme, String location, String price,String description) {
        ImageUrl = imageUrl;
        ProductNAme = productNAme;
        this.location = location;
        this.price = price;
        this.description = description;
    }

    public Modelling() {
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getProductNAme() {
        return ProductNAme;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setProductNAme(String productNAme) {
        ProductNAme = productNAme;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
