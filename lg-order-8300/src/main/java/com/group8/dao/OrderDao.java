package com.group8.dao;

import com.group8.entity.LgGroup;
import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;
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

    List<LgTourOrder> getNotPayOrder(int userId);

    List<LgTourOrder> getPayOrder(int userId);

    List<LgTourOrder> getAllOrderById(int userId);

    List<LgTourOrder> getNoCommentOrder(int userId);

    List<LgGroup> findGroup(String groupName);
}
