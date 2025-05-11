package com.pricecomparator.price_comparator.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricecomparator.price_comparator.dto.PricePoint;
import com.pricecomparator.price_comparator.model.Product;
import com.pricecomparator.price_comparator.util.ProductDataLoader;

@RestController
public class PriceHistoryCtrl {

    private final ProductDataLoader loader = new ProductDataLoader();

    @GetMapping("/price-history")
    public List<PricePoint> getPriceHistory(
            @RequestParam String productId,
            @RequestParam List<String> files
    ) {
        List<PricePoint> result = new ArrayList<>();

        for (String file : files) {
            List<Product> products = loader.loadProducts(file);
            for (Product product : products) {
                if (product.getProductId().equals(productId)) {
                    result.add(new PricePoint(product.getDate(), product.getPrice()));
                }
            }
        }

        result.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        return result;
    }
}
