package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: AvgMark <br>
 * date: 2022/2/23 11:42 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvgMarkDto {
    private Integer pid;
    private Integer mark;
}
