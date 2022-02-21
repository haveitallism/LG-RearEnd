package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgNormalUserScenicspotCollect {

  private long userId;
  private long scenicId;
  private java.sql.Timestamp collectTime;


}
