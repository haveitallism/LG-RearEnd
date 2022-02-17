package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgComment {

    private long commentId;
    private String commentContent;
    private String commentFid;
    private String praiseNum;
    private String createBy;
    private java.sql.Timestamp createTime;

}
