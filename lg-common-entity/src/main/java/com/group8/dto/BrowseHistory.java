package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author laiyong
 * @date 2022/2/18 16:41 星期五
 * @apiNote 浏览历史类，封装用户id、浏览项目的id及项目类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowseHistory<T> {

    private long userId; // 浏览者id
    private T browsed; // 被浏览的东西（商品，攻略，游记等）

}
