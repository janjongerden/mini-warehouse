package nl.janjongerden.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContainedArticleDto {

    private long art_id;

    private long amount_of;
}
