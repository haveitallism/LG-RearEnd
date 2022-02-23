package com.group8.entity;

import com.group8.utils.CommentLikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LgUserLike {
    private int id;
    private String likedUserId;
    private String likedPostId;
    private Integer status = CommentLikedStatusEnum.UNLIKE.getCode();
    private Date createTime;
    private java.sql.Timestamp updateTime;
    public LgUserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
