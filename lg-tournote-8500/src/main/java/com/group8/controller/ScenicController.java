package com.group8.controller;

import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author acoffee
 * @create 2022-02-17 17:29
 */
@RequestMapping("/scenic")
@RefreshScope
@RestController
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadImg(@RequestBody UploadImg uploadImg){
        System.out.println(uploadImg);
        String imgUrl = scenicService.uploadImg(uploadImg);
        return new ResponseEntity(200,"上传成功！",imgUrl);
    }

    @PostMapping("/test/{str}")
    public ResponseEntity<String> test(@PathVariable("str") String str){
        return new ResponseEntity<>(200, "str", str);
    }
}
