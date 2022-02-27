package com.group8.service.impl;

import com.group8.dao.ScenicSpotCommentDao;
import com.group8.entity.LgComment;
import com.group8.service.ScenicSpotCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ScenicSpotCommentServiceImpl implements ScenicSpotCommentService {
    @Resource
    ScenicSpotCommentDao scenicSpotCommentDao;

    @Override
    public void addScenicSpotComment(int sid, int uid, int fid, String content) {
        LgComment lgComment = new LgComment(0,content,Integer.toString(fid),"0",Integer.toString(uid),new Timestamp(System.currentTimeMillis()),null);
        scenicSpotCommentDao.addScenicSpotComment(lgComment);
        scenicSpotCommentDao.addMiddule(sid,lgComment.getCommentId());
    }

    @Override
    public void replayScenicSpotComment(int id, int uid, int fid, int mark, String content) {
        LgComment lgComment = new LgComment(0,content,Integer.toString(fid),"0",Integer.toString(uid),new Timestamp(System.currentTimeMillis()),null);
        scenicSpotCommentDao.addScenicSpotComment(lgComment);
    }

    @Override
    public List<LgComment> findAll(int id) {
        return scenicSpotCommentDao.findAll(id);
    }

    @Override
    public void update(int cid) {
        scenicSpotCommentDao.update(cid);
    }

    @Override
    public void delete(int cid) {
        scenicSpotCommentDao.delete(cid);
    }


}
