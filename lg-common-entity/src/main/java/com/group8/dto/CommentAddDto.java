package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {
    int id;
    int uid;
    int fid;
    int mark;//商品分数
    String content;
}
