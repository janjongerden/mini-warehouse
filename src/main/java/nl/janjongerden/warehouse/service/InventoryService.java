package nl.janjongerden.warehouse.service;

import nl.janjongerden.warehouse.dto.InventoryDto;
import nl.janjongerden.warehouse.entity.Inventory;
import nl.janjongerden.warehouse.exception.InsufficientStockException;
import nl.janjongerden.warehouse.exception.UnknownArticleException;
import nl.janjongerden.warehouse.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    /**
     * Sets inventory to items and clears old inventory.
     */
    @Transactional
    public void setInventory(Collection<InventoryDto> items) {
        repository.deleteAll();
        repository.saveAll(items.stream()
                .map(InventoryService::dtoToEntity)
                .collect(Collectors.toSet()));
    }

    public InventoryDto getItem(long articleId) {
        return entityToDto(repository.findById(articleId)
                .orElseThrow(() -> new UnknownArticleException("Article not found with id " + articleId)));
    }

    @Transactional
    public void reduceStock(long articleId, long reduction) {
        Inventory article = repository.getById(articleId);
        if (article.getStock() < reduction) {
            throw new InsufficientStockException("Only " + article.getStock() + " items left.");
        }
        article.setStock(article.getStock() - reduction);
        repository.save(article);
    }

    public static Inventory dtoToEntity(InventoryDto dto) {
        return Inventory.builder()
                .articleId(dto.getArt_id())
                .name(dto.getName())
                .stock(dto.getStock())
                .build();
    }

    public static InventoryDto entityToDto(Inventory entity) {
        return InventoryDto.builder()
                .art_id(entity.getArticleId())
                .name(entity.getName())
                .stock(entity.getStock())
                .build();
    }
}
