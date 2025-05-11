package com.pricecomparator.price_comparator.dto;

public class OptimizedProduct {
    private final String productId;
    private final String productName;
    private final double price;

    public OptimizedProduct(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}
