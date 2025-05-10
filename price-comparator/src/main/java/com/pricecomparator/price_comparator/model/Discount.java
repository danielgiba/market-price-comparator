package com.pricecomparator.price_comparator.model;

public class Discount {
    private String productId;
    private String productName;
    private double originalPrice;
    private double discountedPrice;
    private String store;
    private double discountPercent;

    public Discount() {}

    public Discount(String productId, String productName, double originalPrice, double discountedPrice, String store) {
        this.productId = productId;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.store = store;
        this.discountPercent = calculateDiscountPercent(originalPrice, discountedPrice);
    }

    private double calculateDiscountPercent(double originalPrice, double discountedPrice) {
        if (originalPrice <= 0) return 0;
        return Math.round((1 - discountedPrice / originalPrice) * 10000.0) / 100.0; // ex: 24.75
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", originalPrice=" + originalPrice +
                ", discountedPrice=" + discountedPrice +
                ", store='" + store + '\'' +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
