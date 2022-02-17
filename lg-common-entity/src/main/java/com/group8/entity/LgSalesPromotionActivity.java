package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgSalesPromotionActivity {

    private long activityId;
    private String activityType;
    private long inventory;
    private long activityPrice;
    private long productId;
    private java.sql.Timestamp openTime;
    private java.sql.Timestamp endTime;
    private java.sql.Timestamp scopeOfActivities;
    private long activityStatus;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
