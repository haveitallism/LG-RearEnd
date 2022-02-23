package com.group8.controller;

import com.group8.dto.CommentAddDto;
import com.group8.entity.LgComment;
import com.group8.entity.ResponseEntity;
import com.group8.service.ProductCommentService;
import com.group8.service.ScenicSpotCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("scenicspotcomment")
public class ScenicSpotCommentController {

    @Resource
    ScenicSpotCommentService scenicSpotCommentService;

    /**
     * 攻略评论添加
     * @param commentAddDto
     * @return
     */
    @PostMapping("add")
    public ResponseEntity add(@RequestBody CommentAddDto commentAddDto){
        scenicSpotCommentService.addScenicSpotComment(commentAddDto.getId(), commentAddDto.getUid(), commentAddDto.getFid(), commentAddDto.getContent());
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 查询所有评论
     * @param id
     * @return
     */
    @GetMapping("findAll/{id}")
    public ResponseEntity findAll(int id){
        List<LgComment> all = scenicSpotCommentService.findAll(id);
        return new ResponseEntity(200,"ok",all);
    }

    /**
     * 评论点赞
     * @param cid
     * @return
     */
    @PutMapping("update/{cid}")
    public ResponseEntity update(int cid){
        scenicSpotCommentService.update(cid);
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @DeleteMapping("delete/{cid}")
    public ResponseEntity delete(int cid){
        scenicSpotCommentService.delete(cid);
        return new ResponseEntity(200,"ok","success");
    }
}
