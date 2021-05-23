package nl.janjongerden.warehouse.service;

import nl.janjongerden.warehouse.dto.ContainedArticleDto;
import nl.janjongerden.warehouse.dto.InventoryDto;
import nl.janjongerden.warehouse.dto.ProductDto;
import nl.janjongerden.warehouse.entity.ContainedArticle;
import nl.janjongerden.warehouse.entity.Product;
import nl.janjongerden.warehouse.exception.InsufficientStockException;
import nl.janjongerden.warehouse.exception.UnknownArticleException;
import nl.janjongerden.warehouse.exception.UnknownProductException;
import nl.janjongerden.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public void addProducts(List<ProductDto> products) {
        assureAllArticlesExist(products);

        repository.saveAll(products.stream()
                .map(ProductService::dtoToEntity)
                .collect(toSet()));
    }

    @Transactional
    public List<ProductDto> getAllProducts() {
        return repository.findAll().stream()
                .map(p -> entityToDtoWithStock(p, stockFor(p)))
                .collect(toList());
    }

    @Transactional
    public void purchaseProduct(String productName, long amount) {
        Product product = repository.findById(productName)
                .orElseThrow(() -> new UnknownProductException("No product found named " + productName));
        for (ContainedArticle article : product.getArticles()) {
            try {
                inventoryService.reduceStock(article.getArticleId(), article.getAmount() * amount);
            } catch (InsufficientStockException e) {
                throw new InsufficientStockException("Not enough stock for this purchase");
            }
        }
    }

    private void assureAllArticlesExist(List<ProductDto> products) {
        for (ProductDto product : products) {
            for (ContainedArticleDto article : product.getContain_articles()) {
                InventoryDto item = inventoryService.getItem(article.getArt_id());
                if (item == null) {
                    throw new UnknownArticleException(String.format("Unknown article id %s in product %s", article.getArt_id(), product.getName()));
                }
            }
        }

    }

    private long stockFor(Product product) {
        return product.getArticles().stream()
                .map(a -> inventoryService.getItem(a.getArticleId()).getStock() / a.getAmount())
                .mapToLong(n -> n)
                .min().orElse(0L);
    }

    private static Product dtoToEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .articles(dto.getContain_articles().stream()
                        .map(a -> new ContainedArticle(a.getArt_id(), a.getAmount_of()))
                        .collect(toList()))
                .build();
    }

    private static ProductDto entityToDtoWithStock(Product entity, long stock) {
        return ProductDto.builder()
                .name(entity.getName())
                .contain_articles(entity.getArticles().stream()
                        .map(a -> new ContainedArticleDto(a.getArticleId(), a.getAmount()))
                        .collect(toList()))
                .numberOnStock(stock)
                .build();
    }
}
