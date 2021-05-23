# mini-warehouse
Simple warehouse service

## Starting up the warehouse

To run the warehouse locally on port 8080 including a demo data set, run:

```
./runDemo.sh
```

Which will start the service in the background, wait for 
the service to be up and insert demo data from the `/demo` folder.

To start an empty service you can alternatively run:

```
./gradlew bootRun
```

## Endpoints

### Inventory

```
-- reset the inventory of the warehouse
POST /inventory

curl -X POST http://localhost:8080/inventory \
--data '{"inventory": [{"art_id": "513","name": "part","stock": "5"}]}' \
--header "Content-Type: application/json"
```

### Products

```
-- add products
POST /products

curl -X POST http://localhost:8080/products \
--data '{"products": [{"name": "MyProduct","contain_articles": [{"art_id": "513","amount_of": "2"}]}]}' \
--header "Content-Type: application/json"

-- show all products
GET /products

curl -X GET http://localhost:8080/products
```

### Purchases

```
-- purchase a number of products
POST /purchases

curl -X POST http://localhost:8080/purchases \
--data '{"productName": "MyProduct","amount": "2"}]}' \
--header "Content-Type: application/json"
```
