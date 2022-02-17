package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgBussinessUser {
    private long bussId;
    private String bussName;
    private String bussCompanyName;
    private String bussPassword;
    private String bussEmail;
    private String bussTelphone;
    private String bussHeadImg;
    private String bussIntro;
    private String bussScale;
    private String bussAddress;
    private long bussLicenseNum;
    private String bussLicenseImg;
    private long bussStatus;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;
}
