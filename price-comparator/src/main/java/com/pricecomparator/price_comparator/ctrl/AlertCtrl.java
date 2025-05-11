package com.pricecomparator.price_comparator.ctrl;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricecomparator.price_comparator.dto.PriceAlert;
import com.pricecomparator.price_comparator.model.Product;
import com.pricecomparator.price_comparator.util.ProductDataLoader;

@RestController
public class AlertCtrl {

    private final ProductDataLoader loader = new ProductDataLoader();

    @GetMapping("/alert/check")
    public PriceAlert checkPriceAlert(
            @RequestParam String productId,
            @RequestParam double target,
            @RequestParam String file
    ) {
        List<Product> products = loader.loadProducts(file);
        Optional<Product> match = products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst();

        if (match.isPresent()) {
            Product p = match.get();
            boolean triggered = p.getPrice() <= target;
            return new PriceAlert(triggered, p.getPrice());
        } else {
            throw new RuntimeException("Product not found in file: " + productId);
        }
    }
}
