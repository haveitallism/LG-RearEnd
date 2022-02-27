package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private int cid;
    private String name;
    private int id;
    private String headImg;
    private String comment;
    private Timestamp time;
    private int commentNum;
    private int like;
    private int status;
    private boolean inputShow = false;
    private List<CommentReplayResponse> reply;
}
