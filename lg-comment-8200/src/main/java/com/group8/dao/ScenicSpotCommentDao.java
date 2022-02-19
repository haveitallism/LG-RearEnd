package com.group8.dao;

import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScenicSpotCommentDao {

    void addMiddule(@Param("sid") int sid, @Param("cid") long commentId);

    List<LgComment> findAll(int id);
    List<LgComment> findSon(int id);

    void update(int cid);

    void delete(int cid);

    void addScenicSpotComment(LgComment lgComment);
}
