package com.group8.controller;

import com.group8.dto.CommentAddDto;
import com.group8.dto.CommentResponse;
import com.group8.entity.LgComment;
import com.group8.entity.ResponseEntity;
import com.group8.service.ProductCommentService;
import com.group8.service.TravelNoteCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("productcomment")
public class ProductCommentController {

    @Resource
    ProductCommentService productCommentService;
    @PostMapping("test")
    public ResponseEntity test(@RequestBody CommentAddDto commentAddDto){
        return new ResponseEntity(200,"ok","success");
    }
    /**
     * 商品评论添加
     * @param commentAddDto
     * @return
     */
    @PostMapping("add")
    public ResponseEntity add(@RequestBody CommentAddDto commentAddDto){
        productCommentService.addProductComment(commentAddDto.getId(), commentAddDto.getUid(), commentAddDto.getFid(), commentAddDto.getMark(), commentAddDto.getContent(),commentAddDto.getOid());
        return new ResponseEntity(200,"ok","success");
    }

    @PostMapping("reply")
    public ResponseEntity replay(@RequestBody CommentAddDto commentAddDto){
        System.out.println(commentAddDto);
        productCommentService.replayProductComment(commentAddDto.getId(), commentAddDto.getUid(), commentAddDto.getFid(), commentAddDto.getMark(), commentAddDto.getContent());
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 查询所有评论
     * @param id    商品id
     * @param userId 用户id
     * @return
     */
    @PostMapping("findAll/{id}/{userId}")
    public ResponseEntity findAll(@PathVariable int id, @PathVariable int userId){
        List<CommentResponse> all = productCommentService.findAll(id,userId);
        return new ResponseEntity(200,"ok",all);
    }

    /**
     * 评论点赞
     * @param cid
     * @return
     */
    @PutMapping("update/{cid}")
    public ResponseEntity update(@PathVariable int cid){
        productCommentService.update(cid);
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @DeleteMapping("delete/{cid}")
    public ResponseEntity delete(@PathVariable int cid){
        productCommentService.delete(cid);
        return new ResponseEntity(200,"ok","success");
    }
}
