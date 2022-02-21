package com.group8.feignClient;

import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author laiyong
 * @date 2022/2/19 16:20 星期六
 * @apiNote
 */
@FeignClient("lg-tournote-8500")
@RequestMapping("/scenic")
public interface TourNoteClient {

    @PostMapping("/test/{str}")
    ResponseEntity<String> test(@PathVariable("str") String str);

}
