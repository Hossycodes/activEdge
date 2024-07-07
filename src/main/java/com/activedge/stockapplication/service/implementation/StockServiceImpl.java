package com.activedge.stockapplication.service.implementation;

import com.activedge.stockapplication.dto.request.StockRequestDto;
import com.activedge.stockapplication.dto.response.StockResponseDto;
import com.activedge.stockapplication.dto.response.UpdateStockResponse;
import com.activedge.stockapplication.exception.StockNotFoundException;
import com.activedge.stockapplication.model.Stock;
import com.activedge.stockapplication.repository.StockRepository;
import com.activedge.stockapplication.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    @Override
    public StockResponseDto createStock(StockRequestDto stockRequestDto) {
        Stock stock = new Stock();
        stock.setName(stockRequestDto.getName());
        stock.setCurrentPrice(stockRequestDto.getCurrentPrice());

        Stock savedStock = stockRepository.save(stock);

        return new StockResponseDto(savedStock);
    }

    @Override
    public UpdateStockResponse updateStock(Long stockId, StockRequestDto request) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock with Id " + stockId + " not found"));
        if (request.getCurrentPrice() != null) {
            stock.setCurrentPrice(request.getCurrentPrice());
        }
        if (request.getName() != null) {
            stock.setName(request.getName());
        }
        Stock updatedStock = stockRepository.save(stock);
        return new UpdateStockResponse(updatedStock);
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException("Stock not found with id " + id));
    }

    @Override
    public List<Stock> getAllStocks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return stockRepository.findAll(pageable).getContent();
    }
}
