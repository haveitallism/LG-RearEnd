package com.group8.service;

import com.group8.entity.LgComment;

import java.util.List;

public interface ScenicSpotCommentService {
    /**
     * 攻略评论的添加
     * @param sid
     * @param uid
     * @param fid
     * @param content
     */
    void addScenicSpotComment(int sid, int uid,int fid,String content);

    List<LgComment> findAll(int id);

    void update(int cid);

    void delete(int cid);


    void replayScenicSpotComment(int id, int uid, int fid, int mark, String content);
}
