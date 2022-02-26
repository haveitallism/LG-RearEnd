package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author laiyong
 * @date 2022/2/24 22:31 星期四
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNum {
    private int unpaid;
    private int uncomment;
}
