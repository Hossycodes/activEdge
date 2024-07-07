package com.activedge.stockapplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockRequestDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "current price is required")
    private BigDecimal currentPrice;
}
