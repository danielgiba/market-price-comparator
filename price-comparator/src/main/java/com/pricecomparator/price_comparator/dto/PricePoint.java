package com.pricecomparator.price_comparator.dto;

import java.time.LocalDate;

public class PricePoint {
    private final LocalDate date;
    private final double price;

    public PricePoint(LocalDate date, double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}
