#!/bin/bash

# start the service
./gradlew bootRun &

# wait for health endpoint
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' localhost:8080/actuator/health)" != "200" ]];
 do echo "Waiting for service to start" && sleep 1;
done

echo "Loading demo inventory"
curl -X POST http://localhost:8080/inventory -d @demo/inventory.json --header "Content-Type: application/json"

echo "Loading demo products"
curl -X POST http://localhost:8080/products -d @demo/products.json --header "Content-Type: application/json"

echo "Current product list:"
curl -X GET http://localhost:8080/products