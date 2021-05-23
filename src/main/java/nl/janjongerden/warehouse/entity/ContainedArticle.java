package nl.janjongerden.warehouse.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@Table(name = "contained_article")
@NoArgsConstructor
public class ContainedArticle {

    public ContainedArticle(long articleId, long amount) {
        this.articleId = articleId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue
    private Long id;

    private long articleId;

    @PositiveOrZero
    private long amount;
}
