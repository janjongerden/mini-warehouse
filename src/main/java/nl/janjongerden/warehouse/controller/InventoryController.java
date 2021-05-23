package nl.janjongerden.warehouse.controller;

import nl.janjongerden.warehouse.dto.InventoryListDto;
import nl.janjongerden.warehouse.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void setInventory(@RequestBody InventoryListDto inventory) {
        service.setInventory(inventory.getInventory());
    }
}
