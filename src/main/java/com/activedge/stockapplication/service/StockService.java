package com.activedge.stockapplication.service;

import com.activedge.stockapplication.dto.request.StockRequestDto;
import com.activedge.stockapplication.dto.response.StockResponseDto;
import com.activedge.stockapplication.dto.response.UpdateStockResponse;
import com.activedge.stockapplication.model.Stock;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface StockService {
    public StockResponseDto createStock(StockRequestDto stockRequestDto);
    public UpdateStockResponse updateStock(Long stockId, StockRequestDto request);
    public Stock getStockById(Long id);
    public List<Stock> getAllStocks(int page, int size);
}
