package com.group8.controller;

import com.group8.entity.LgGroup;
import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;
import com.group8.entity.ResponseEntity;
import com.group8.feignClient.GroupClient;
import com.group8.feignClient.ScenicClient;
import com.group8.feignClient.TravelNoteClient;
import com.group8.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laiyong
 * @date 2022/2/23 09:48 星期三
 * @apiNote
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired(required = false)
    GroupClient groupClient;
    @Autowired(required = false)
    ScenicClient scenicClient;
    @Autowired(required = false)
    TravelNoteClient travelNoteClient;

    @GetMapping("/getIndexData")
    public ResponseEntity<Map<String, Object>> getIndexData(){
        Map<String, Object> indexMap = new HashMap<>();
        // 调用其他微服务查询信息
        ResponseEntity<List<LgGroup>> featuredGroup = groupClient.featuredGroup("default");
        ResponseEntity<List<LgScenicspot>> scenicByDownloadsNum = scenicClient.findScenicByDownloadsNum();
        ResponseEntity<List<LgTravelnotes>> travelNotesByPraiseNum = travelNoteClient.findTravelNotesByPraiseNum();
        // 放入集合中
        indexMap.put("group", featuredGroup.getData());
        indexMap.put("scenic", scenicByDownloadsNum.getData());
        indexMap.put("travelNotes", travelNotesByPraiseNum.getData());
        return new ResponseEntity<>(200, "查询成功！", indexMap);
    }

}
