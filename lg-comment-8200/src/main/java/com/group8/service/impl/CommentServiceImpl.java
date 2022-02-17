package com.group8.service.impl;

import com.group8.dao.TravelNoteCommentDao;
import com.group8.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    TravelNoteCommentDao travelNoteCommentDao;
    @Override
    public void addTravelNotesComment(int uid, int fid, String content) {

        travelNoteCommentDao.addTravelNoteCommentDao(uid,fid,content);
    }

    @Override
    public void addProductComment(int uid, int fid, String content) {

    }

    @Override
    public void addScenicSpotComment(int uid, int fid, String content) {

    }
}
