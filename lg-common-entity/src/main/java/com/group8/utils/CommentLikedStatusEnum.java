package com.group8.utils;

import lombok.Getter;

@Getter
public enum CommentLikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
            ;

    private Integer code;

    private String msg;

    CommentLikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
