package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgGroup {

    private long groupId;
    private long bussId;
    private String groupName;
    private String groupCover;
    private int lowestPrice;
    private long groupSold;
    private long groupScore;
    private long groupFavorites;
    private String groupIntroduce;
    private String groupRoute;
    private String groupFeeExplanation;
    private String groupDestineNotice;
    private String groupPurchaseNotice;
    private String groupDeparture;
    private long groupStatus;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;
    // 套餐集合
    private List<LgCombo> comboList;
    // 所属商家对象
    private LgBussinessUser bussinessUser;
    // 日库存对象集合
    private List<LgDailyStock> dailyStockList;
}
