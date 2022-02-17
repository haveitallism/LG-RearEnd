package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgTourOrder {

    private long orderId;
    private long productId;
    private long userId;
    private long orderChildNum;
    private long orderAdultNum;
    private String orderChoose;
    private String bookName;
    private String bookTel;
    private String orderStartingTime;
    private double orderAmount;
    private long orderPayoutStatus;
    private long orderStatus;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
