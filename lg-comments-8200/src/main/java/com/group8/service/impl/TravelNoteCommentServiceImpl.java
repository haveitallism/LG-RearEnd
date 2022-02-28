package com.group8.service.impl;

import com.group8.dao.TravelNoteCommentDao;
import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import com.group8.service.TravelNoteCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TravelNoteCommentServiceImpl implements TravelNoteCommentService {

    @Resource
    TravelNoteCommentDao travelNoteCommentDao;

    /**
     * 添加评论
     * @param nid
     * @param uid
     * @param fid
     * @param content
     */
    @Override
    public void addTravelNotesComment(int nid,int uid, int fid, String content) {
        LgComment lgComment = new LgComment(0,content,Integer.toString(fid),"0",Integer.toString(uid),new Timestamp(System.currentTimeMillis()),null);
        travelNoteCommentDao.addTravelNoteCommentDao(lgComment);
        travelNoteCommentDao.addMiddule(nid,lgComment.getCommentId());
    }

    @Override
    public void replayTravelNoteComment(int id, int uid, int fid, int mark, String content) {
        LgComment lgComment = new LgComment(0,content,Integer.toString(fid),"0",Integer.toString(uid),new Timestamp(System.currentTimeMillis()),null);
        travelNoteCommentDao.addTravelNoteCommentDao(lgComment);
    }

    /**
     * 查找评论
     * @param id
     * @return
     */
    @Override
    public List<CommentResponse> findAll(int id, int userId) {
        return travelNoteCommentDao.findAll(id,userId);

    }

    /**
     * 点赞
     * @param cid
     */
    @Override
    public void update(int cid) {
        travelNoteCommentDao.update(cid);
    }

    /**
     * 删除评论
     * @param cid
     */
    @Override
    public void delete(int cid) {
        travelNoteCommentDao.delete(cid);
    }




}
