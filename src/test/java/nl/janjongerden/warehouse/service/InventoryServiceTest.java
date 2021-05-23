package nl.janjongerden.warehouse.service;

import nl.janjongerden.warehouse.dto.InventoryDto;
import nl.janjongerden.warehouse.exception.InsufficientStockException;
import nl.janjongerden.warehouse.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InventoryServiceTest {

    private static final long ARTICLE_ID = 45L;
    private static final String ARTICLE_NAME = "Nine Inch Nail";
    private static final long ARTICLE_STOCK = 10L;

    private static InventoryDto itemDto;

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private static InventoryRepository repository;

    @BeforeAll
    public static void setup() {
        itemDto = InventoryDto.builder()
                .art_id(ARTICLE_ID)
                .name(ARTICLE_NAME)
                .stock(ARTICLE_STOCK)
                .build();
    }

    @Test
    public void getInventoryItem() {
        when(repository.getById(ARTICLE_ID)).thenReturn(InventoryService.dtoToEntity(itemDto));

        assertThat(inventoryService.getItem(ARTICLE_ID), is(itemDto));
    }

    @Test
    public void stockReductionLimit() {
        when(repository.getById(ARTICLE_ID)).thenReturn(InventoryService.dtoToEntity(itemDto));

        assertThrows(InsufficientStockException.class,() ->
                inventoryService.reduceStock(ARTICLE_ID, ARTICLE_STOCK + 1));
    }
}