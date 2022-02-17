package com.group;

import com.group8.CommentApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest(classes = CommentApplication.class)
public class CommentTest {
    @Test
    void test1(){
        Date date = new Date();
        System.out.println(date);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }
}
