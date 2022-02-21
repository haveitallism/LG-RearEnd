package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgNormalUserTravelnotesCollect {

  private long userId;
  private long notesId;
  private java.sql.Timestamp collectTime;




}
