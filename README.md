# Price Comparator Backend

Acest proiect reprezintă o aplicație Java Spring Boot care permite compararea prețurilor produselor alimentare între mai multe supermarketuri (ex: Lidl, Kaufland, Profi). Scopul este de a evidenția reducerile de preț și de a sprijini utilizatorii în luarea unor decizii de cumpărare informate.

---

## Structura proiectului

- `model/`
  - `Product.java`: Reprezintă structura unui produs (nume, brand, cantitate, preț, magazin, etc).
  - `Discount.java`: Reprezintă o reducere identificată între două fișiere CSV pentru același produs (conține prețul vechi, cel nou și procentul de reducere).

- `util/`
  - `ProductDataLoader.java`: Citește și parsează fișiere CSV în obiecte de tip `Product`.
  - `DiscountAnalyzer.java`: Compară două liste de produse și generează o listă de reduceri.

- `ctrl/`
  - `ProductCtrl.java`: Controller REST care expune endpointul `/products` pentru a încărca produsele dintr-un fișier CSV.
  - `DiscountCtrl.java`: Controller REST care expune endpointul `/discounts` pentru a compara două fișiere CSV și a returna reducerile.

- `PriceComparatorApplication.java`: Clasa principală care pornește aplicația Spring Boot.

---

## Cerințe

- JDK 17
- Maven 3.8+
- Spring Boot 3.4.5
- OpenCSV 5.8

---

## Cum se rulează aplicația

1. Clonare repository:
   ```bash
   git clone https://github.com/danielgiba/market-price-comparator.git
   cd market-price-comparator
   ```

2. Rulare aplicație:
   ```bash
   mvn spring-boot:run
   ```

3. Serverul pornește implicit pe:  
   `http://localhost:8080`

---

## Structura fișierelor CSV

### Exemple:
Fișierele CSV se află în `src/main/resources/data/` și au următorul format:

#### Produse:
```
product_id;product_name;product_category;brand;package_quantity;package_unit;price;currency
P001;lapte zuzu;lactate;Zuzu;1;l;9.80;RON
...
```

---

## Funcționalități implementate

### 1. Listarea produselor dintr-un fișier CSV

**Endpoint:**
```
GET /products?file=nume_fisier.csv
```

**Exemplu:**
```
http://localhost:8080/products?file=lidl_2025-05-08.csv
```

---

### 2. Afișarea reducerilor de preț între două fișiere

**Endpoint:**
```
GET /discounts?oldFile=vechi.csv&newFile=nou.csv
```

**Parametri opționali:**
- `minPercent` – filtrează reducerile mai mari decât un anumit procent.
- `sortBy` – ordonează rezultatele după `percent`, `price` sau `name`.

**Exemplu:**
```
http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv&minPercent=10&sortBy=percent
```

---

## Decizii de design și presupuneri

- Fișierele CSV sunt localizate în folderul `resources/data/`.
- Fiecare combinație produs + magazin este unică.
- Se presupune că fișierele CSV sunt corect formatate.
- Nu a fost folosită o bază de date. Totul se lucrează în memorie.
- Nu se gestionează reducerile expirate, ci doar comparația între două fișiere.

---

## Extensibilitate

Proiectul poate fi extins cu următoarele:
- Salvarea istoricului prețurilor într-o bază de date.
- Exportul reducerilor în fișiere.
- Recomandări pe baza valorii per unitate (kg, l, etc).
- Notificări automate când apar reduceri noi.

---

## Cum pot fi testate endpointurile

Folosind un browser sau Postman:

- **Listare produse:**
  ```
  http://localhost:8080/products?file=kaufland_2025-05-08.csv
  ```

- **Comparare reduceri între două fișiere:**
  ```
  http://localhost:8080/discounts?oldFile=kaufland_2025-05-01.csv&newFile=kaufland_2025-05-08.csv
  ```

- **Filtrare după reducere:**
  ```
  http://localhost:8080/discounts?oldFile=lidl_2025-05-01.csv&newFile=lidl_2025-05-08.csv&minPercent=15
  ```

---



