package com.group8.controller;

import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.ResponseEntity;
import com.group8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
//@RequestMapping("/order")
public class OrderController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderService orderService;



    @PostConstruct
    public String start(){
        List<LgSalesPromotionActivity> activities = orderService.getAllActivity();
        ListOperations opsForList = redisTemplate.opsForList();
        for(LgSalesPromotionActivity activity : activities){
//            redisTemplate.opsForValue().set("activity"+activity.getActivityId(), activity.getInventory());
            int num =(int) activity.getInventory();
            for(int i = 1;i <= num; i++){
                opsForList.leftPush("activity"+activity.getActivityId(),i);
            }
        }
        return "ok";
    }

    //秒杀
    @RequestMapping("/seckill/{activityId}")
    public ResponseEntity<String> second(@PathVariable("activityId") int activityId){
        //创建订单
        orderService.seckill(activityId);
        return new ResponseEntity(200,"秒杀成功",null);
    }


}
