package com.group8.service;

public interface CommentService {
    /**
     * 游记评论
     * @param uid
     * @param fid
     * @param content
     */
    void addTravelNotesComment(int uid,int fid,String content);

    /**
     * 商品评论
     * @param uid
     * @param fid
     * @param content
     */
    void addProductComment(int uid,int fid,String content);

    /**
     * 攻略评论
     * @param uid
     * @param fid
     * @param content
     */
    void addScenicSpotComment(int uid,int fid,String content);
}
