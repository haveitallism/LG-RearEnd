package com.group8.feignClient;

import com.group8.entity.LgGroup;
import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/19 16:20 星期六
 * @apiNote
 */
@FeignClient("lg-group-8400")
@RequestMapping("/group")
public interface GroupClient {

    @GetMapping("/featuredGroup")
    public ResponseEntity<List<LgGroup>> featuredGroup(@RequestParam("currentSortType") String currentSortType);

}
