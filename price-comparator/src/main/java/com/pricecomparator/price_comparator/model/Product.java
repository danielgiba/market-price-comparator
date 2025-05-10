package com.pricecomparator.price_comparator.model;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

public class Product {

    @CsvBindByName(column = "product_id")
    private String productId;

    @CsvBindByName(column = "product_name")
    private String productName;

    @CsvBindByName(column = "product_category")
    private String productCategory;

    @CsvBindByName
    private String brand;

    @CsvBindByName(column = "package_quantity")
    private double packageQuantity;

    @CsvBindByName(column = "package_unit")
    private String packageUnit;

    @CsvBindByName
    private double price;

    @CsvBindByName
    private String currency;

    private String store;
    private LocalDate date;

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductCategory() { return productCategory; }
    public void setProductCategory(String productCategory) { this.productCategory = productCategory; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPackageQuantity() { return packageQuantity; }
    public void setPackageQuantity(double packageQuantity) { this.packageQuantity = packageQuantity; }

    public String getPackageUnit() { return packageUnit; }
    public void setPackageUnit(String packageUnit) { this.packageUnit = packageUnit; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStore() { return store; }
    public void setStore(String store) { this.store = store; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", brand='" + brand + '\'' +
                ", packageQuantity=" + packageQuantity +
                ", packageUnit='" + packageUnit + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", store='" + store + '\'' +
                ", date=" + date +
                '}';
    }
}
