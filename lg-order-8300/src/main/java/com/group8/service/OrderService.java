package com.group8.service;

import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;

import java.util.List;


public interface OrderService {

    public void start();
    public void seckill(int activityId);

    LgSalesPromotionActivity getActivityById(int activityId);

    void addOrder(LgTourOrder lgTourOrder);

    long deductInventory(int activityId);

    List<LgSalesPromotionActivity> getAllActivity();
}
