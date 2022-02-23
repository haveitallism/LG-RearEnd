package com.group8.dao;

import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCommentDao {
    void addProductCommentDao(LgComment lgComment);

    void addMiddule(@Param("pid") int pid, @Param("cid") long commentId, @Param("mark") int mark);

    List<CommentResponse> findAll(int id);
    List<LgComment> findSon(int fid);
    String getImg();
    String getUserName();
    int getCommentNum();
    String getToName();
    int getToId();

    void update(int cid);

    void delete(int cid);

    void updateProductMark(int pid);
}
