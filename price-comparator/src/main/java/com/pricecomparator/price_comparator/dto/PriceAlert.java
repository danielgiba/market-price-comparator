package com.pricecomparator.price_comparator.dto;

public class PriceAlert {
    private final boolean triggered;
    private final double currentPrice;

    public PriceAlert(boolean triggered, double currentPrice) {
        this.triggered = triggered;
        this.currentPrice = currentPrice;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}
