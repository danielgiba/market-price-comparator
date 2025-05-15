# Market Price Comparator – Java Spring Boot

This project is a backend web application developed in Java using Spring Boot. Its purpose is to compare product prices between different supermarket chains (such as Lidl, Kaufland, and Profi), identify discounts, track price changes over time, and provide tools to optimize a shopping basket or trigger alerts when certain price thresholds are reached.

The project was created as part of the Accesa Java Internship 2025 challenge, where participants were asked to implement useful features based on real-world price tracking needs.

---

## Project Structure

The application is organized using a clean, layered architecture that separates business logic, data models, utility classes, and REST controllers. Here's a breakdown of the main packages:

### `model/`

Contains core domain classes.

- `Product.java`  
  Represents a single product in a supermarket. Each product has attributes such as ID, name, brand, category, quantity, unit, price, store, and date (derived from the CSV file it came from).

- `Discount.java`  
  Represents a discount found between two CSV files, including the product’s old and new prices, the store, and the calculated discount percentage.

### `dto/`

Contains Data Transfer Objects that help shape specific API responses without exposing the internal structure of models.

- `PricePoint.java`: used for returning a product's price on a specific date in the price history endpoint.
- `OptimizedProduct.java`: a simplified product representation used in shopping basket optimization results.
- `BasketOptResult.java`: represents the full result of basket optimization (store name, total price, and product list).
- `PriceAlert.java`: represents whether a product has reached a price below a specified threshold and what the current price is.

### `util/`

Contains helper classes for reading data and performing price comparisons.

- `ProductDataLoader.java`: loads product data from CSV files stored in `resources/data/` and attaches store and date metadata based on the filename.
- `DiscountAnalyzer.java`: contains logic for computing price reductions between product datasets, as well as detecting newly introduced discounts.

### `ctrl/`

Exposes the API endpoints via Spring REST controllers.

- `ProductCtrl.java`: handles `/products`, allowing users to load and filter products by file, brand, category, store, and sort them.
- `DiscountCtrl.java`: provides both `/discounts` and `/discounts/new`, allowing full discount comparison or just new discounts compared to the previous file.
- `PriceHistoryCtrl.java`: exposes `/price-history`, allowing users to view how a product's price changed over time across multiple files.
- `BasketCtrl.java`: implements `/basket/optimize`, returning the cheapest store for a set of products.
- `AlertCtrl.java`: provides `/alert/check`, which verifies if a product has reached a user-defined price alert threshold.

---

## Requirements

- Java 17
- Maven 3.8 or newer
- Spring Boot 3.4.5
- OpenCSV 5.8

This project does not use a database; all processing happens in-memory.

---

## Running the Application

1. Clone the repository:

```bash
git clone https://github.com/danielgiba/market-price-comparator.git
cd market-price-comparator
```

2. Run the application using Maven:

```bash
mvn spring-boot:run
```

3. The app will be available at:

```
http://localhost:8080
```

Sample CSV files can be found under:

```
src/main/resources/data/
```

---

## CSV File Format

Each CSV represents product data from one store at a specific date. Filenames must follow the format:

```
[store]_[yyyy-MM-dd].csv
```

Example line from `lidl_2025-05-08.csv`:

```
product_id;product_name;product_category;brand;package_quantity;package_unit;price;currency
P001;lapte zuzu;lactate;Zuzu;1;l;9.80;RON
```

---

## Implemented Features

### 1. List Products

Returns all products from a given CSV file.

```
http://localhost:8080/products?file=lidl_2025-05-08.csv

```

Supports optional filters:
- `brand`
- `category`
- `store`
- `sortBy=name|price|brand`

---

### 2. Compare Discounts Between Two Files

Returns products with price drops.

```
http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv
```

### 3. Show Discounts as "%"

Returns only newly introduced discounts not present in the old file.

```
http://localhost:8080/discounts/new?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv
```

---

### 4. Get Price History for a Product

Returns all known prices for a given product across multiple dates.

```
http://localhost:8080/price-history?productId=P001&files=lidl_2025-05-01.csv,lidl_2025-05-08.csv
```

---

### 5. Optimize Shopping Basket

Returns the store with the lowest total price for a set of products.

```
http://localhost:8080/basket/optimize?products=P001,P005,P017&files=lidl_2025-05-08.csv,profi_2025-05-08.csv,kaufland_2025-05-08.csv
```

---

### 6. Trigger a Custom Price Alert

Checks whether a product’s current price is lower than or equal to a given threshold.

```
http://localhost:8080/alert/check?productId=P001&target=9.80&file=lidl_2025-05-08.csv
```

---


---



