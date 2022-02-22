package com.group8;

import com.group8.service.TravelNoteCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest(classes = CommentsApplication8200.class)
public class CommentTest {
    @Resource
    TravelNoteCommentService travelNoteCommentService;

    @Test
    void test1(){
        Date date = new Date();
        System.out.println(date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
    @Test
    void test2(){
        //travelNoteCommentService.addTravelNotesComment(1,1,2,"aa");
//        List<LgComment> all = travelNoteCommentService.findAll(1);
//        System.out.println(all);
        //travelNoteCommentService.addTravelNotesComment(0,1,0,"aa");
        travelNoteCommentService.update(1);
    }
}
