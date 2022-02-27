package com.group8.controller;

import com.group8.entity.LgUserLike;
import com.group8.entity.ResponseEntity;
import com.group8.service.LikeCommentRedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("click")
public class LikeController {
    @Resource
    LikeCommentRedisService likeCommentRedisService;

    @PostMapping("like")
    public ResponseEntity like(@RequestBody LgUserLike lgUserLike){
        System.out.println(lgUserLike);
        if(lgUserLike.getStatus() == 1){
            likeCommentRedisService.saveLiked2Redis(lgUserLike.getLikedUserId(),lgUserLike.getLikedPostId());
            likeCommentRedisService.incrementLikedCount(lgUserLike.getLikedUserId());
        }else {
            likeCommentRedisService.unlikeFromRedis(lgUserLike.getLikedUserId(),lgUserLike.getLikedPostId());
            likeCommentRedisService.decrementLikedCount(lgUserLike.getLikedUserId());
        }
        return new ResponseEntity(200,"ok","success");
    }

}
