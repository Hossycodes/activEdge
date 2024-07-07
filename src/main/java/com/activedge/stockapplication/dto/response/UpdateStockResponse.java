package com.activedge.stockapplication.dto.response;

import com.activedge.stockapplication.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockResponse {
    private Long id;
    private String name;
    private BigDecimal currentPrice;
    private Timestamp lastUpdate;

    public UpdateStockResponse(Stock stock) {
        this.id = stock.getId();
        this.name = stock.getName();
        this.currentPrice = stock.getCurrentPrice();
        this.lastUpdate = stock.getLastUpdate();
    }
}
