package com.group8.controller;

import com.group8.dto.CommentAddDto;
import com.group8.entity.LgComment;
import com.group8.entity.ResponseEntity;
import com.group8.service.TravelNoteCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("travelnotecomment")
public class TravelNoteCommentController {

    @Resource
    TravelNoteCommentService travelNoteCommentService;

    /**
     * 游记评论添加
     * @param commentAddDto
     * @return
     */
    @PostMapping("add")
    public ResponseEntity addTravelNoteComment(@RequestBody CommentAddDto commentAddDto){
        travelNoteCommentService.addTravelNotesComment(commentAddDto.getId(), commentAddDto.getUid(), commentAddDto.getFid(), commentAddDto.getContent());
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 游记回复
     * @param commentAddDto
     * @return
     */
    @PostMapping("reply")
    public ResponseEntity replay(@RequestBody CommentAddDto commentAddDto){
        System.out.println(commentAddDto);
        travelNoteCommentService.replayTravelNoteComment(commentAddDto.getId(), commentAddDto.getUid(), commentAddDto.getFid(), commentAddDto.getMark(), commentAddDto.getContent());
        return new ResponseEntity(200,"ok","success");
    }
    /**
     * 查询所有评论
     * @param id
     * @return
     */
    @GetMapping("findAll/{id}")
    public ResponseEntity findAllTravelNoteComment(int id){
        List<LgComment> all = travelNoteCommentService.findAll(id);
        return new ResponseEntity(200,"ok",all);
    }

    /**
     * 评论点赞
     * @param cid
     * @return
     */
    @PutMapping("update/{cid}")
    public ResponseEntity updateTravelNoteComment(int cid){
        travelNoteCommentService.update(cid);
        return new ResponseEntity(200,"ok","success");
    }

    /**
     * 删除评论
     * @param cid
     * @return
     */
    @DeleteMapping("delete/{cid}")
    public ResponseEntity deleteTravelNoteComment(int cid){
        travelNoteCommentService.delete(cid);
        return new ResponseEntity(200,"ok","success");
    }
}
