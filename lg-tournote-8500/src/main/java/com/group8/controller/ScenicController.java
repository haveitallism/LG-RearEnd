package com.group8.controller;

import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author acoffee
 * @create 2022-02-17 17:29
 */
@RequestMapping("/scenic")
@RestController
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadImg(UploadImg uploadImg){
        System.out.println(uploadImg);
        String imgUrl = scenicService.uploadImg(uploadImg);
        return new ResponseEntity(200,"上传成功！",imgUrl);
    }
}
