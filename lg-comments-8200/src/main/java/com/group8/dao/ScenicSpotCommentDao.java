package com.group8.dao;

import com.group8.dto.CommentReplayResponse;
import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScenicSpotCommentDao {

    void addMiddule(@Param("sid") int sid, @Param("cid") long commentId);

    List<CommentResponse> findAll(@Param("id") int id, @Param("uid") int userId);
    List<CommentReplayResponse> findSon(int id);
    String getImg();
    String getUserName();
    int getCommentNum();
    String getToName();
    int getToId();
    void update(int cid);

    void delete(int cid);

    void addScenicSpotComment(LgComment lgComment);
}
