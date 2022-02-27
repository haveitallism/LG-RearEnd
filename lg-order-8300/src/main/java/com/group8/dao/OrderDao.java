package com.group8.dao;

import com.group8.dto.UserOrders;
import com.group8.entity.LgGroup;
import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;
import com.group8.entity.LgTourOrderPersonalInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    void addOrder(LgTourOrder lgTourOrder);

    void getOrder();

    LgSalesPromotionActivity getActivityById(int activityId);

    void deductInventory(int activityId);

    int getInventory(int activityId);

    List<LgSalesPromotionActivity> getAllActivity();

    void updateInventory(@Param("size") Long size, @Param("activityId") Long activityId);

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
}
