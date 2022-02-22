package com.group8.service.impl;

import com.group8.dao.OrderDao;
import com.group8.entity.LgSalesPromotionActivity;
import com.group8.entity.LgTourOrder;
import com.group8.service.OrderService;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedissonClient redissonClient;

    //将数据从数据库存到redis
    @Override
    public void start(){
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        for(int i = 1 ; i <= 1000; i++){
            opsForList.leftPush("list", i);
        }
    }

    //秒杀
    @Override
    public void seckill(int activityId){
        //减redis库存
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        Object inventory =  opsForList.rightPop("activity" + activityId);
        if(inventory == null){
            System.out.println("over");
        }else {
            //创建秒杀订单
            LgTourOrder lgTourOrder = new LgTourOrder();
            lgTourOrder.setProductId(activityId);
            lgTourOrder.setOrderChoose("冰火两重天");
            lgTourOrder.setOrderPayoutStatus(0);
            rabbitTemplate.convertAndSend("exchangeadd", "add", lgTourOrder);
            System.out.println("啦啦啦");
//        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
//        opsForList.leftPush("acticityOrder"+activityId, lgTourOrder);
            //orderDao.addOrder(lgTourOrder);

            //减库存
            //long num = orderService.deductInventory(activityId);
//        long num = redisTemplate.opsForValue().decrement("activity"+activityId);
//        if(num < 0){
//            throw new RuntimeException("商品已售罄");
//        }
        }

    }

    @Override
    public LgSalesPromotionActivity getActivityById(int activityId) {
        return orderDao.getActivityById(activityId);
    }

    @Override
    public void addOrder(LgTourOrder lgTourOrder) {
        orderDao.addOrder(lgTourOrder);
    }

    @Override
    public long deductInventory(int activityId) {
        orderDao.deductInventory(activityId);
        LgSalesPromotionActivity lgSalesPromotionActivity = orderDao.getActivityById(activityId);
        return lgSalesPromotionActivity.getInventory();
    }

    @Override
    public List<LgSalesPromotionActivity> getAllActivity() {
        return orderDao.getAllActivity();
    }


}
