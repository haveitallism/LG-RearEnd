package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgNormalUserGroupCollect {

  private long userId;
  private long groupId;
  private java.sql.Timestamp collectTime;



}
