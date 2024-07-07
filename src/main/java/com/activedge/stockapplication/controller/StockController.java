package com.activedge.stockapplication.controller;

import com.activedge.stockapplication.dto.request.StockRequestDto;
import com.activedge.stockapplication.dto.response.ApiResponse;
import com.activedge.stockapplication.dto.response.StockResponseDto;
import com.activedge.stockapplication.dto.response.UpdateStockResponse;
import com.activedge.stockapplication.model.Stock;
import com.activedge.stockapplication.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<StockResponseDto>> createStock(@RequestBody StockRequestDto stockRequestDto) {
        StockResponseDto stock = stockService.createStock(stockRequestDto);
        return new ResponseEntity<>(new ApiResponse<>("201", "Stock created successfully", stock, "CREATED"), HttpStatus.CREATED);
    }
    @PutMapping("/update-stock/{id}")
    public ResponseEntity<ApiResponse<UpdateStockResponse>> updateStock(@PathVariable Long id, @RequestBody StockRequestDto stockRequestDto) {
        UpdateStockResponse updateStockResponse = stockService.updateStock(id, stockRequestDto);
        return new ResponseEntity<>(new ApiResponse<>("200", "Stock updated successfully", updateStockResponse, "OK"), HttpStatus.OK);
    }
    @GetMapping("/get-stock/{id}")
    public ResponseEntity<ApiResponse<Stock>> getStockById(@PathVariable Long id) {
        Stock stock = stockService.getStockById(id);
        return new ResponseEntity<>(new ApiResponse<>("200", "Success", stock, "OK"), HttpStatus.OK);
    }
    @GetMapping("/get-all-stocks")
    public ResponseEntity<ApiResponse<List<Stock>>> getAllStocks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Stock> stocks = stockService.getAllStocks(page, size);
        return new ResponseEntity<>(new ApiResponse<>("200", "Success", stocks, "OK"), HttpStatus.OK);
    }

}
