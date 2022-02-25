package com.group8.service;

import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;

import java.util.List;


public interface OrderService {

    public void start();
    public void seckill(int activityId);

    LgSalesPromotionActivity getActivityById(int activityId);

    void addOrder(LgTourOrder lgTourOrder);



    List<LgSalesPromotionActivity> getAllActivity();

    void updateInventory();

    List<LgTourOrder> getAllOrder(LgTourOrder lgTourOrder);

    List<LgTourOrder> getNotPayOrder(int userId);

    List<LgTourOrder> getPayOrder(int userId);

    List<LgTourOrder> getAllOrderById(int userId);

    List<LgTourOrder> getNoCommentOrder(int userId);
}
