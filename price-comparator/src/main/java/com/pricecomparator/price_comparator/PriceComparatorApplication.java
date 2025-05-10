package com.pricecomparator.price_comparator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}

}

// 1. testare afisare toate produsele dintr-un csv

// http://localhost:8080/products?file=lidl_2025-05-08.csv
// http://localhost:8080/products?file=profi_2025-05-08.csv
// http://localhost:8080/products?file=kaufland_2025-05-01.csv

// 2. ca sa afiseze toate reducerile dintre 2 csv-uri -> reducerile de la lidl

// http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv
// la profi e null : http://localhost:8080/discounts?oldFile=profi_2025-05-01.csv&newFile=profi_2025-05-08.csv
// http://localhost:8080/discounts?oldFile=kaufland_2025-05-01.csv&newFile=kaufland_2025-05-08.csv

// 3. sortarea dupa:

// nume produs : http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv&sortBy=name
// pret redus : http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv&sortBy=price
// procentul mare: http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv&sortBy=percent





