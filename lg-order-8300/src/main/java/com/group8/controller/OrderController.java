package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.OrderFindByPage;
import com.group8.dto.UserOrders;
import com.group8.entity.*;
import com.group8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    OrderService orderService;



    @RequestMapping("/start")
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
    public ResponseEntity<LgTourOrder> getAllOrder(@RequestBody OrderFindByPage orderFindByPage){
        PageHelper.startPage(orderFindByPage.getPage(),orderFindByPage.getLimit());
        List<LgTourOrder> list = orderService.getAllOrder(orderFindByPage.getLgTourOrder());
        PageInfo<LgTourOrder> lgTourOrderPageInfo = new PageInfo<>(list);
        return new ResponseEntity(200,"查询成功",lgTourOrderPageInfo);
    }

    @RequestMapping("/getNotPayOrder/{userId}")
    public ResponseEntity<List<UserOrders>> getNotPayOrder(@PathVariable("userId")int userId){
        List<UserOrders> list = orderService.getNotPayOrder(userId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/getPayOrder/{userId}")
    public ResponseEntity<List<UserOrders>> getPayOrder(@PathVariable("userId")int userId){
        List<UserOrders> list = orderService.getPayOrder(userId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/getAllOrderById/{userId}")
    public ResponseEntity<List<UserOrders>> getAllOrderById(@PathVariable("userId")int userId){
        List<UserOrders> list = orderService.getAllOrderById(userId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/getNoCommentOrder/{userId}")
    public ResponseEntity<List<UserOrders>> getNoCommentOrder(@PathVariable("userId")int userId){
        List<UserOrders> list = orderService.getNoCommentOrder(userId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/findGroup/{groupName}")
    public ResponseEntity<LgGroup> findGroup(@PathVariable("groupName") String groupName){
        List<LgGroup> list = orderService.findGroup(groupName);
        return new ResponseEntity(200,"搜索成功",list);
    }

    @RequestMapping("/getNoGoOrder/{userId}")
    public ResponseEntity<List<UserOrders>> getNOGoOrder(@PathVariable("userId")int userId){
        List<UserOrders> list = orderService.getNOGoOrder(userId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(200,"删除成功","删除成功");
    }

    @RequestMapping("/getAllPersons/{orderId}")
    public ResponseEntity<LgTourOrderPersonalInformation> getAllPersons(@PathVariable("orderId") int orderId){
        List<LgTourOrderPersonalInformation> list = orderService.getAllPersons(orderId);
        return new ResponseEntity(200,"查询成功",list);
    }

    @RequestMapping("/findBookPerson/{orderId}")
    public ResponseEntity<LgTourOrder> findBookPerson(@PathVariable("orderId") int orderId){
        List<LgTourOrder> list = orderService.findBookPerson(orderId);
        return new ResponseEntity(200,"查询成功",list);
    }
}
