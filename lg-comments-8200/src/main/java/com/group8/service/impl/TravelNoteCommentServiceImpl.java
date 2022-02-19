package com.group8.service.impl;

import com.group8.dao.TravelNoteCommentDao;
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
        if(fid == 0){
            travelNoteCommentDao.addMiddule(nid,lgComment.getCommentId());
        }
    }

    /**
     * 查找评论
     * @param id
     * @return
     */
    @Override
    public List<LgComment> findAll(int id) {
        List<LgComment> lgComments = travelNoteCommentDao.findAll(id);
        return lgComments;
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
