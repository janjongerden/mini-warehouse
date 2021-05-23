package nl.janjongerden.warehouse.controller;

import nl.janjongerden.warehouse.dto.ProductDto;
import nl.janjongerden.warehouse.dto.ProductListDto;
import nl.janjongerden.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProducts(@RequestBody ProductListDto products) {
        service.addProducts(products.getProducts());
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

}
