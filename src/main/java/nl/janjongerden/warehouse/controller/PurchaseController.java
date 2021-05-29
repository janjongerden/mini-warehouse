package nl.janjongerden.warehouse.controller;

import nl.janjongerden.warehouse.dto.PurchaseDto;
import nl.janjongerden.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseProduct(@RequestBody PurchaseDto purchase) {
        service.purchaseProduct(purchase.getProductName(), purchase.getAmount());
    }
}
