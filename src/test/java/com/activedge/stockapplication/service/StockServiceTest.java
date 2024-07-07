package com.activedge.stockapplication.service;

import com.activedge.stockapplication.dto.request.StockRequestDto;
import com.activedge.stockapplication.dto.response.StockResponseDto;
import com.activedge.stockapplication.dto.response.UpdateStockResponse;
import com.activedge.stockapplication.exception.StockNotFoundException;
import com.activedge.stockapplication.model.Stock;
import com.activedge.stockapplication.repository.StockRepository;
import com.activedge.stockapplication.service.implementation.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    public void testCreateStock() {
        // Given
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setName("New Stock");
        stockRequestDto.setCurrentPrice(BigDecimal.valueOf(100));

        Stock stock = new Stock();
        stock.setName(stockRequestDto.getName());
        stock.setCurrentPrice(stockRequestDto.getCurrentPrice());

        Stock savedStock = new Stock();
        savedStock.setId(1L);
        savedStock.setName(stockRequestDto.getName());
        savedStock.setCurrentPrice(stockRequestDto.getCurrentPrice());

        when(stockRepository.save(any(Stock.class))).thenReturn(savedStock);

        // When
        StockResponseDto response = stockService.createStock(stockRequestDto);

        // Then
        assertEquals("New Stock", response.getName());
        assertEquals(BigDecimal.valueOf(100), response.getCurrentPrice());
        assertEquals(1L, response.getId());
    }
    @Test
    public void testUpdateStock() {
        // Given
        Long stockId = 1L;
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setName("Updated Stock");
        stockRequestDto.setCurrentPrice(BigDecimal.valueOf(200));

        Stock existingStock = new Stock();
        existingStock.setId(stockId);
        existingStock.setName("Old Stock");
        existingStock.setCurrentPrice(BigDecimal.valueOf(100));

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(existingStock));
        when(stockRepository.save(any(Stock.class))).thenReturn(existingStock);

        // When
        UpdateStockResponse response = stockService.updateStock(stockId, stockRequestDto);

        // Then
        assertEquals("Updated Stock", response.getName());
        assertEquals(BigDecimal.valueOf(200), response.getCurrentPrice());
    }

    @Test
    public void testUpdateStock_NotFound() {
        // Given
        Long stockId = 1L;
        StockRequestDto stockRequestDto = new StockRequestDto();
        stockRequestDto.setName("Updated Stock");
        stockRequestDto.setCurrentPrice(BigDecimal.valueOf(200));

        when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(StockNotFoundException.class, () -> stockService.updateStock(stockId, stockRequestDto));
    }
    @Test
    public void testGetStockById() {
        // Given
        Long stockId = 1L;

        Stock stock = new Stock();
        stock.setId(stockId);
        stock.setName("Stock1");
        stock.setCurrentPrice(BigDecimal.TEN);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        Stock result = stockService.getStockById(stockId);

        // Then
        assertEquals("Stock1", result.getName());
        assertEquals(BigDecimal.TEN, result.getCurrentPrice());
    }
    @Test
    public void testGetAllStocks() {
        // Given
        Stock stock1 = new Stock();
        stock1.setId(1L);
        stock1.setName("Stock1");
        stock1.setCurrentPrice(BigDecimal.TEN);

        Stock stock2 = new Stock();
        stock2.setId(2L);
        stock2.setName("Stock2");
        stock2.setCurrentPrice(BigDecimal.valueOf(20));

        List<Stock> stocks = Arrays.asList(stock1, stock2);
        Page<Stock> page = new PageImpl<>(stocks);
        Pageable pageable = PageRequest.of(0, 10);

        when(stockRepository.findAll(pageable)).thenReturn(page);

        // When
        List<Stock> result = stockService.getAllStocks(0, 10);

        // Then
        assertEquals(2, result.size());
        assertEquals("Stock1", result.get(0).getName());
        assertEquals("Stock2", result.get(1).getName());
    }

}
