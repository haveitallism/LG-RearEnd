package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrders {
    private long orderId;
    private long productId;
    private String groupName;
    private Date orderStartingTime;
    private double orderAmount;
    private long orderStatus;
    private Date createdTime;
    private long commentId;
    private long orderPayoutStatus;
}
