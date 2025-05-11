package com.pricecomparator.price_comparator.dto;

import java.util.List;

public class BasketOptResult {
    private final String store;
    private final double total;
    private final List<OptimizedProduct> products;

    public BasketOptResult(String store, double total, List<OptimizedProduct> products) {
        this.store = store;
        this.total = total;
        this.products = products;
    }

    public String getStore() {
        return store;
    }

    public double getTotal() {
        return total;
    }

    public List<OptimizedProduct> getProducts() {
        return products;
    }
}
