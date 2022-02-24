package com.group8.feignClient;

import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;
import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/19 16:20 星期六
 * @apiNote
 */
@FeignClient("lg-tourNote-8500")
@RequestMapping("/travelnotes")
public interface TravelNoteClient {

    //根据点赞量查询游记并降序排列
    @RequestMapping("/findTravelNotesByPraiseNum")
    public ResponseEntity<List<LgTravelnotes>> findTravelNotesByPraiseNum();

}
