package com.group8.feignClient;

import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;
import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/19 16:20 星期六
 * @apiNote
 */
@FeignClient("lg-tourNote-8500")
@RequestMapping("/scenic")
public interface ScenicClient {

    //根据下载量查询攻略并降序排列
    @RequestMapping("/findScenicByDownloadsNum")
    public ResponseEntity<List<LgScenicspot>> findScenicByDownloadsNum();

}
