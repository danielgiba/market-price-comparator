package com.pricecomparator.price_comparator.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricecomparator.price_comparator.dto.BasketOptResult;
import com.pricecomparator.price_comparator.dto.OptimizedProduct;
import com.pricecomparator.price_comparator.model.Product;
import com.pricecomparator.price_comparator.util.ProductDataLoader;

@RestController
public class BasketCtrl {

    private final ProductDataLoader loader = new ProductDataLoader();

    @GetMapping("/basket/optimize")
    public BasketOptResult optimizeBasket(
            @RequestParam List<String> products,
            @RequestParam List<String> files
    ) {
        Map<String, List<Product>> storeMap = new HashMap<>();

        for (String file : files) {
            List<Product> fileProducts = loader.loadProducts(file);
            if (fileProducts.isEmpty()) continue;
            String store = fileProducts.get(0).getStore();
            storeMap.computeIfAbsent(store, k -> new ArrayList<>()).addAll(fileProducts);
        }

        String bestStore = null;
        double minTotal = Double.MAX_VALUE;
        List<OptimizedProduct> bestProducts = new ArrayList<>();

        for (Map.Entry<String, List<Product>> entry : storeMap.entrySet()) {
            String store = entry.getKey();
            List<Product> available = entry.getValue();
            List<OptimizedProduct> current = new ArrayList<>();
            double sum = 0;
            boolean allFound = true;

            for (String pid : products) {
                Optional<Product> match = available.stream()
                        .filter(p -> p.getProductId().equals(pid))
                        .findFirst();

                if (match.isPresent()) {
                    Product p = match.get();
                    current.add(new OptimizedProduct(p.getProductId(), p.getProductName(), p.getPrice()));
                    sum += p.getPrice();
                } else {
                    allFound = false;
                    break;
                }
            }

            if (allFound && sum < minTotal) {
                minTotal = sum;
                bestStore = store;
                bestProducts = current;
            }
        }

        return new BasketOptResult(bestStore, minTotal, bestProducts);
    }
}
