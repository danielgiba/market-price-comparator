package com.pricecomparator.price_comparator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pricecomparator.price_comparator.model.Discount;
import com.pricecomparator.price_comparator.model.Product;

public class DiscountAnalyzer {

    public List<Discount> findDiscounts(List<Product> older, List<Product> newer) {
        Map<String, Product> oldProductMap = new HashMap<>();
        for (Product oldProduct : older) {
            String key = oldProduct.getProductId() + "_" + oldProduct.getStore();
            oldProductMap.put(key, oldProduct);
        }

        List<Discount> discounts = new ArrayList<>();
        for (Product newProduct : newer) {
            String key = newProduct.getProductId() + "_" + newProduct.getStore();
            if (oldProductMap.containsKey(key)) {
                Product oldProduct = oldProductMap.get(key);
                if (newProduct.getPrice() < oldProduct.getPrice()) {
                    discounts.add(new Discount(
                            newProduct.getProductId(),
                            newProduct.getProductName(),
                            oldProduct.getPrice(),
                            newProduct.getPrice(),
                            newProduct.getStore()
                    ));
                }
            }
        }

        return discounts;
    }

    public List<Discount> findNewDiscounts(List<Product> oldProducts, List<Product> newProducts) {
        Map<String, Double> oldPrices = new HashMap<>();
        for (Product p : oldProducts) {
            String key = p.getProductId() + "_" + p.getStore();
            oldPrices.put(key, p.getPrice());
        }

        List<Discount> newDiscounts = new ArrayList<>();
        for (Product p : newProducts) {
            String key = p.getProductId() + "_" + p.getStore();
            if (oldPrices.containsKey(key)) {
                double oldPrice = oldPrices.get(key);
                if (p.getPrice() < oldPrice) {
                    newDiscounts.add(new Discount(
                            p.getProductId(),
                            p.getProductName(),
                            oldPrice,
                            p.getPrice(),
                            p.getStore()
                    ));
                }
            }
        }

        return newDiscounts;
    }
}
