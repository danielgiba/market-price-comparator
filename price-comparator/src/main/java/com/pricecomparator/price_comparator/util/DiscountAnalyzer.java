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
}
