package com.pricecomparator.price_comparator.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.pricecomparator.price_comparator.model.Product;

public class ProductDataLoader {

    public List<Product> loadProducts(String csvFileName) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/" + csvFileName);
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + csvFileName);
            }

            List<Product> products = new CsvToBeanBuilder<Product>(new InputStreamReader(inputStream, java.nio.charset.StandardCharsets.UTF_8))
                    .withType(Product.class)
                    .withSeparator(';') 
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            String[] parts = csvFileName.replace(".csv", "").split("_");
            String store = parts[0];
            LocalDate date = LocalDate.parse(parts[1]);

            for (Product product : products) {
                product.setStore(store);
                product.setDate(date);
            }

            return products;

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to load CSV: " + csvFileName, e);
        }
    }

    public List<Product> loadAll(List<String> csvFileNames) {
        List<Product> all = new ArrayList<>();
        for (String name : csvFileNames) {
            all.addAll(loadProducts(name));
        }
        return all;
    }
}
