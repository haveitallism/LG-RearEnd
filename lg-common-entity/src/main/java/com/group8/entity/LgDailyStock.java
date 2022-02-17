package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgDailyStock {

    private long dailyStockId;
    private String comboId;
    private java.sql.Timestamp dailyStockDate;
    private String price;
    private String dailyStockQuantity;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
