package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgManageUser {

    private long userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userTelphone;
    private String userHeadImg;
    private String userRole;
    private long userStatus;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;


}
