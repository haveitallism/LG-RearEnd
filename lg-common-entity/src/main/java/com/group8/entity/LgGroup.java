package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgGroup {

    private long groupId;
    private long bussId;
    private String groupName;
    private String groupCover;
    private double lowestPrice;
    private long groupSold;
    private String groupScore;
    private String groupFavorites;
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

}
