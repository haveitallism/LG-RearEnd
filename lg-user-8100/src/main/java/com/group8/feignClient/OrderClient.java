package com.group8.feignClient;

import com.group8.entity.LgTourOrder;
import com.group8.entity.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/24 22:22 星期四
 * @apiNote
 */
@FeignClient("lg-order-8300")
@RequestMapping("/order")
public interface OrderClient {

    @RequestMapping("/getNotPayOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getNotPayOrder(@PathVariable("userId") int userId);

    @RequestMapping("/getPayOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getPayOrder(@PathVariable("userId") int userId);

    @RequestMapping("/getNoCommentOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getNoCommentOrder(@PathVariable("userId") int userId);
}
