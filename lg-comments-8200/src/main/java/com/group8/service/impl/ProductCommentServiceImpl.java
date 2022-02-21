package com.group8.service.impl;

import com.group8.dao.ProductCommentDao;
import com.group8.dao.TravelNoteCommentDao;
import com.group8.entity.LgComment;
import com.group8.service.ProductCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {
    @Resource
    ProductCommentDao productCommentDao;

    @Override
    public void addProductComment(int pid, int uid, int fid, String content) {
        LgComment lgComment = new LgComment(0,content,Integer.toString(fid),"0",Integer.toString(uid),new Timestamp(System.currentTimeMillis()),null);
        productCommentDao.addProductCommentDao(lgComment);
        if(fid == 0){
            productCommentDao.addMiddule(pid,lgComment.getCommentId());
        }
    }

    @Override
    public List<LgComment> findAll(int id) {
        return productCommentDao.findAll(id);
    }

    @Override
    public void update(int cid) {
        productCommentDao.update(cid);
    }

    @Override
    public void delete(int cid) {
        productCommentDao.delete(cid);
    }
}
