package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgDailyStock {

    private long groupId;
    private long comboId;
    private java.sql.Date dailyStockDate;
    private int price;
    private String dailyStockQuantity;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
