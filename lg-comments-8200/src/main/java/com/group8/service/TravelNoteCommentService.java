package com.group8.service;

import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import java.util.List;

public interface TravelNoteCommentService {
    /**
     * 游记评论的添加
     * @param uid
     * @param fid
     * @param content
     */
    void addTravelNotesComment(int nid, int uid,int fid,String content);

    List<CommentResponse> findAll(int id, int userId);

    void update(int cid);

    void delete(int cid);

    void replayTravelNoteComment(int id, int uid, int fid, int mark, String content);
}
