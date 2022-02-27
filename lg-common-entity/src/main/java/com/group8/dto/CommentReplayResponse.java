package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentReplayResponse {
    private int cid;
    private String from;
    private int id;
    private String fromHeadImg;
    private String to;
    private int toId;
    private String comment;
    private Timestamp time;
    private int commentNum;
    private int like;
    private int status;
    private boolean inputShow = false;
}
