# mini-warehouse
Simple warehouse service that stores an inventory list and product definitions. 
Making a purchase from the warehouse will reduce the stock of parts needed for that product.

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

## Considerations/future work

Some reflections on design decisions and steps to make the service production ready.

### Database
Used an H2 in-memory database for easy deployment. In production a 'real' database, such as postgres would make 
more sense.
Choosing a relational database seems a relatively good fit, though a non-sql db such as mongo could also work, as
the data is sent around as json mostly.

### Service decomposition
For now one big service for inventory, products and purchases is okay. However, when more functionality is
added or scaling is need, splitting up in separate services may proof useful.

### Security
No authorization framework is in place yet, which will most likely be needed for production.

### GUI
A web interface on top of the REST interdace to easily upload inventory and products or do purchases would be nice.