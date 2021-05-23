package nl.janjongerden.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDto {

    private String productName;

    private long amount;
}
