package com.pricecomparator.price_comparator.ctrl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricecomparator.price_comparator.model.Discount;
import com.pricecomparator.price_comparator.model.Product;
import com.pricecomparator.price_comparator.util.DiscountAnalyzer;
import com.pricecomparator.price_comparator.util.ProductDataLoader;

@RestController
public class DiscountCtrl {

    private final ProductDataLoader loader = new ProductDataLoader();
    private final DiscountAnalyzer analyzer = new DiscountAnalyzer();

    @GetMapping("/discounts")
    public List<Discount> getDiscounts(
            @RequestParam String oldFile,
            @RequestParam String newFile,
            @RequestParam(required = false) Double minPercent,
            @RequestParam(required = false) String sortBy
    ) {
        List<Product> oldProducts = loader.loadProducts(oldFile);
        List<Product> newProducts = loader.loadProducts(newFile);

        List<Discount> discounts = analyzer.findDiscounts(oldProducts, newProducts);

        if (minPercent != null) {
            discounts = discounts.stream()
                    .filter(d -> d.getDiscountPercent() >= minPercent)
                    .collect(Collectors.toList());
        }

        if (sortBy != null) {
            switch (sortBy) {
                case "percent" -> discounts.sort(Comparator.comparingDouble(Discount::getDiscountPercent).reversed());
                case "price" -> discounts.sort(Comparator.comparingDouble(Discount::getDiscountedPrice));
                case "name" -> discounts.sort(Comparator.comparing(Discount::getProductName));
            }
        }

        return discounts;
    }

    @GetMapping("/discounts/new")
    public List<Discount> getNewDiscounts(
            @RequestParam String oldFile,
            @RequestParam String newFile
    ) {
        List<Product> oldProducts = loader.loadProducts(oldFile);
        List<Product> newProducts = loader.loadProducts(newFile);
        return analyzer.findNewDiscounts(oldProducts, newProducts);
    }
}
