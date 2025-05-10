package com.pricecomparator.price_comparator.ctrl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricecomparator.price_comparator.model.Product;
import com.pricecomparator.price_comparator.util.ProductDataLoader;

@RestController
public class ProductCtrl {

    private final ProductDataLoader loader = new ProductDataLoader();

    @GetMapping("/products")
    public List<Product> getProducts(
            @RequestParam String file,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String store,
            @RequestParam(required = false) String sortBy
    ) {
        List<Product> products = loader.loadProducts(file);

        // Filtrare
        if (category != null) {
            products = products.stream()
                    .filter(p -> p.getProductCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (brand != null) {
            products = products.stream()
                    .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                    .collect(Collectors.toList());
        }

        if (store != null) {
            products = products.stream()
                    .filter(p -> p.getStore().equalsIgnoreCase(store))
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "price" -> products.sort(Comparator.comparingDouble(Product::getPrice));
                case "name" -> products.sort(Comparator.comparing(Product::getProductName));
                case "brand" -> products.sort(Comparator.comparing(Product::getBrand));
            }
        }

        return products;
    }
}
