package com.group8.controller;

import com.group8.dto.DownFile;
import com.group8.entity.LgScenicspot;
import com.group8.service.ScenicService;
import com.group8.dto.UploadImg;
import com.group8.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author acoffee
 * @create 2022-02-17 17:29
 */
@RequestMapping("/scenic")
@RefreshScope
@RestController
@CrossOrigin
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    //上传图片
    @PostMapping("/uploadImg")
    public ResponseEntity<String> uploadImg(@RequestBody UploadImg uploadImg){
        System.out.println(uploadImg);
        String imgUrl = scenicService.uploadImg(uploadImg);
        return new ResponseEntity(200, "上传成功！", imgUrl);
    }

    //上传攻略
    @PostMapping("/uploadStrategy")
    public ResponseEntity<String> uploadStrategy(UploadImg uploadImg) {
        String strategy = scenicService.uploadStrategy(uploadImg);
        return new ResponseEntity(200, "上传成功！", strategy);
    }

    //下载攻略
    @PostMapping("/downloadStrategy")
    public ResponseEntity<String> downloadStrategy(DownFile file) {
        String strategy = scenicService.downloadStrategy(file);
        return new ResponseEntity<>(200, "下载成功！", strategy);
    }

    //添加景点攻略
    @PostMapping("/addScenicspot")
    public ResponseEntity<String> addScenicspot(@RequestBody LgScenicspot lgScenicspot) {
        boolean flag = scenicService.addScenicspot(lgScenicspot);
        if (flag) {
            return new ResponseEntity<String>(200, "添加成功！", null);
        } else {
            return new ResponseEntity<String>(500,"添加失败！",null);
        }
    }

    //修改景点信息
    @PostMapping("/updateScenicspot")
    public ResponseEntity<String> updateScenicspot(@RequestBody LgScenicspot lgScenicspot){
        boolean flag = scenicService.updateScenicspot(lgScenicspot);
        if (flag) {
            return new ResponseEntity<String>(200, "更新成功！", null);
        } else {
            return new ResponseEntity<String>(500,"更新失败！",null);
        }
    }

    //删除景点
    @RequestMapping("/deleteScenicspot/{scenicId}")
    public ResponseEntity<String> deleteScenicspot(@PathVariable int scenicId){
        boolean flag = scenicService.deleteScenicspot(scenicId);
        if (flag) {
            return new ResponseEntity<String>(200, "删除成功！", null);
        } else {
            return new ResponseEntity<String>(500,"删除失败！",null);
        }
    }

    @PostMapping("/test/{str}")
    public ResponseEntity<String> test(@PathVariable("str") String str){
        return new ResponseEntity<>(200, "str", str);
    }

    //根据Id查询景点信息
    @RequestMapping("/findScenicspot/{scenicId}")
    public ResponseEntity<LgScenicspot> findScenicspot(@PathVariable int scenicId){
        LgScenicspot scenicspotInfo = scenicService.findScenicspot(scenicId);
        if (scenicspotInfo != null) {
            return new ResponseEntity<LgScenicspot>(200, "查询成功！", scenicspotInfo);
        } else {
            return new ResponseEntity<LgScenicspot>(500,"查询失败！",null);
        }
    }
}


