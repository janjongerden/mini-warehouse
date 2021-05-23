package nl.janjongerden.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    private Long articleId;

    @Column
    @NotBlank
    private String name;

    @Column
    @PositiveOrZero
    private long stock;
}
