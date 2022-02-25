package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.OrderFindByPage;
import com.group8.entity.LgGroup;
import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;
import com.group8.entity.ResponseEntity;
import com.group8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderService orderService;



    @PostConstruct
    public ResponseEntity<String> start(){
        List<LgSalesPromotionActivity> activities = orderService.getAllActivity();
        ListOperations opsForList = redisTemplate.opsForList();
        for(LgSalesPromotionActivity activity : activities){
//            redisTemplate.opsForValue().set("activity"+activity.getActivityId(), activity.getInventory());
            int num =(int) activity.getInventory();
            for(int i = 1;i <= num; i++){
                opsForList.leftPush("activity"+activity.getActivityId(),i);
            }
        }
        return new ResponseEntity(200,"加载缓存",null);
    }

    //秒杀
    @RequestMapping("/seckill/{activityId}")
    public ResponseEntity<String> second(@PathVariable("activityId") int activityId){
        //创建订单
        orderService.seckill(activityId);
        return new ResponseEntity(200,"秒杀成功",null);
    }

    @RequestMapping("/getAllOrder")
    public ResponseEntity<PageInfo<LgTourOrder>> getAllOrder(@RequestBody OrderFindByPage orderFindByPage){
        PageHelper.startPage(orderFindByPage.getPage(),orderFindByPage.getLimit());
        List<LgTourOrder> list = orderService.getAllOrder(orderFindByPage.getLgTourOrder());
        PageInfo<LgTourOrder> lgTourOrderPageInfo = new PageInfo<>(list);
        return new ResponseEntity<>(200,"查询成功",lgTourOrderPageInfo);
    }

    @RequestMapping("/getNotPayOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getNotPayOrder(@PathVariable("userId") int userId){
        List<LgTourOrder> list = orderService.getNotPayOrder(userId);
        return new ResponseEntity<>(200,"查询成功",list);
    }

    @RequestMapping("/getPayOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getPayOrder(@PathVariable("userId")int userId){
        List<LgTourOrder> list = orderService.getPayOrder(userId);
        return new ResponseEntity<>(200,"查询成功",list);
    }

    @RequestMapping("/getAllOrderById/{userId}")
    public ResponseEntity<List<LgTourOrder>> getAllOrderById(@PathVariable("userId")int userId){
        List<LgTourOrder> list = orderService.getAllOrderById(userId);
        return new ResponseEntity<>(200,"查询成功",list);
    }

    @RequestMapping("/getNoCommentOrder/{userId}")
    public ResponseEntity<List<LgTourOrder>> getNoCommentOrder(@PathVariable("userId")int userId){
        List<LgTourOrder> list = orderService.getNoCommentOrder(userId);
        return new ResponseEntity<>(200,"查询成功",list);
    }

    @RequestMapping("/findGroup/{groupName}")
    public ResponseEntity<LgGroup> findGroup(@PathVariable("groupName") String groupName){
        List<LgGroup> list = orderService.findGroup(groupName);
        return new ResponseEntity(200,"搜索成功",list);
    }
}
