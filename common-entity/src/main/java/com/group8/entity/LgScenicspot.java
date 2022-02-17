package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgScenicspot {

    private long scenicId;
    private String scenicName;
    private String scenicDescription;
    private String scenicCoverUrl;
    private String strategyUrl;
    private String scenicType;
    private long downloadsNum;
    private String createdBy;
    private java.sql.Timestamp createdTime;
    private String updatedBy;
    private java.sql.Timestamp updatedTime;

}
