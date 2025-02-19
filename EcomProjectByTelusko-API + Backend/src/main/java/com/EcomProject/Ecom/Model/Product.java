package com.EcomProject.Ecom.Model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component

//unfortunately below lombok was not working and I spent half an hour finding why it didn't work

//@Data       // lombok used for setters and getters,
//@AllArgsConstructor
//@NoArgsConstructor

@Entity         // used by H2 DB
public class Product {

    @Id     // below is the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // helps to assign product's id automatically like Pre increment property in sql
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")     -- fixed in front end part
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;

//    Image stored below
    private String imageName;
    private String imageType;

    @Lob        // it's a large object that's why this notation
    private byte[] imageData;

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Product() {
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Product(String name, int id, String description, String brand, BigDecimal price, String category, Date releaseDate, boolean avail, int quantity) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.releaseDate = releaseDate;
        this.productAvailable = avail;
        this.stockQuantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public int getQuantity() {
        return stockQuantity;
    }

    public void setQuantity(int quantity) {
        this.stockQuantity = quantity;
    }
}
