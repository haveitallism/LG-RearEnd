package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LgTourGroup {
    private long groupId;
    private long userId;
    private String groupComboName;
    private String dateTime;
    private double price;
}
