package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgCombo {

    private long comboId;
    private String comboName;
    private long groupId;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;
    private long comboStatus;

}
