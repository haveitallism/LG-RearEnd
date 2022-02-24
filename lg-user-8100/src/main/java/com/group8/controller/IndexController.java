package com.group8.controller;

import com.group8.entity.LgGroup;
import com.group8.entity.ResponseEntity;
import com.group8.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/23 09:48 星期三
 * @apiNote
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired(required = false)
    IndexService indexService;

    @GetMapping("/featuredGroup")
    public ResponseEntity<List<LgGroup>> featuredGroup(){
        List<LgGroup> groupList = indexService.featuredGroup();
        if (!groupList.isEmpty()) {
            return new ResponseEntity<>(200, "查询成功！", groupList);
        }else {
            return new ResponseEntity<>(500, "查询失败！", null);
        }
    }

}
