package com.group8.service;

import com.group8.dto.UserOrders;
import com.group8.entity.LgGroup;
import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;
import com.group8.entity.LgTourOrderPersonalInformation;

import java.util.List;


public interface OrderService {

    public void start();
    public void seckill(int activityId);

    LgSalesPromotionActivity getActivityById(int activityId);

    void addOrder(LgTourOrder lgTourOrder);



    List<LgSalesPromotionActivity> getAllActivity();

    void updateInventory();

    List<LgTourOrder> getAllOrder(LgTourOrder lgTourOrder);

    List<UserOrders> getNotPayOrder(int userId);

    List<UserOrders> getPayOrder(int userId);

    List<UserOrders> getAllOrderById(int userId);

    List<UserOrders> getNoCommentOrder(int userId);

    List<LgGroup> findGroup(String groupName);

    List<UserOrders> getNOGoOrder(int userId);

    void deleteOrder(int orderId);

    List<LgTourOrderPersonalInformation> getAllPersons(int orderId);

    List<LgTourOrder> findBookPerson(int orderId);

    LgTourOrder findOrderById(int orderId);
}
