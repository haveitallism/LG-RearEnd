package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {
    int id; //商品id/攻略id/游记id
    int uid;
    int fid;
    int mark;//商品分数
    int oid;
    String content;
    Date date;
    int praiseNum;
}
