package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author laiyong
 * @date 2022/2/25 10:03 星期五
 * @apiNote 记录用户搜索历史
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistory {
    private int id;
    private String keyword;
}
