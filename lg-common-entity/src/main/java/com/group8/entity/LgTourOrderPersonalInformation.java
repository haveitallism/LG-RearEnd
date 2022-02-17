package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgTourOrderPersonalInformation {

    private long infoId;
    private String orderId;
    private String customerName;
    private String customerId;
    private String customerNationality;
    private String customerTelphone;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
