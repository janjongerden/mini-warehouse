package nl.janjongerden.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDto {

    private long art_id;

    private String name;

    private long stock;
}
