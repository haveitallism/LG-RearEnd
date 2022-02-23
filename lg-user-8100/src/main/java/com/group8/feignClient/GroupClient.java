package com.group8.feignClient;

import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laiyong
 * @date 2022/2/19 16:20 星期六
 * @apiNote
 */
@FeignClient("lg-group-8400")
@RequestMapping("/group")
public interface GroupClient {

    @PostMapping("/test/{str}")
    ResponseEntity<String> test(@PathVariable("str") String str);

}
