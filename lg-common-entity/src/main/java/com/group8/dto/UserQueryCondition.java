package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author laiyong
 * @date 2022/2/18 14:06 星期五
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryCondition<T> {

    private int limit;
    private int page;
    private T user;

}
