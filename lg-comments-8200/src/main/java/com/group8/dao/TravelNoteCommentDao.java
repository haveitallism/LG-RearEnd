package com.group8.dao;

import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelNoteCommentDao {

    void addTravelNoteCommentDao(LgComment lgComment);

    List<LgComment> findAll(int id);
    List<LgComment> findSon(int fid);

    void addMiddule(@Param("nid") int nid, @Param("cid") long commentId);

    void update(int cid);

    void delete(int cid);
}
