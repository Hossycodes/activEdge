package com.activedge.stockapplication.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal currentPrice;
    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createDate;
    @Column(name = "lastUpdated_date")
    @UpdateTimestamp
    private Timestamp lastUpdate;
}
