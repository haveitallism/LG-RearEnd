package com.group8.dao;

import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCommentDao {
    void addProductCommentDao(LgComment lgComment);

    void addMiddule(@Param("pid") int pid, @Param("cid") long commentId, @Param("mark") int mark);

    List<CommentResponse> findAll(@Param("id") int id,@Param("uid") int userId);
    List<LgComment> findSon(int fid);
    String getImg();
    String getUserName();
    int getCommentNum();
    String getToName();
    int getToId();

    void update(int cid);

    void delete(int cid);

    void updateProductMark(int pid);

    void updateOrderCommentId(@Param("oid") int oid, @Param("cid") long commentId);
}
