package com.group8.dao;

import com.group8.dto.CommentReplayResponse;
import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelNoteCommentDao {

    void addTravelNoteCommentDao(LgComment lgComment);

    List<CommentResponse> findAll(@Param("id") int id,@Param("uid") int userId);
    List<CommentReplayResponse> findSon(int fid);
    String getImg();
    String getUserName();
    int getCommentNum();
    String getToName();
    int getToId();
    void addMiddule(@Param("nid") int nid, @Param("cid") long commentId);

    void update(int cid);

    void delete(int cid);
}
