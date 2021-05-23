package nl.janjongerden.warehouse.repository;

import nl.janjongerden.warehouse.entity.ContainedArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainedArticleRepository
        extends JpaRepository<ContainedArticle, Long> {

}