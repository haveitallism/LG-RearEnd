package com.group8.service.impl;

import com.group8.dao.OrderDao;
import com.group8.entity.LgGroup;
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
            lgTourOrder.setCommentId(0);
            rabbitTemplate.convertAndSend("exchangeadd", "add", lgTourOrder);

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
    public List<LgSalesPromotionActivity> getAllActivity() {
        return orderDao.getAllActivity();
    }

    @Override
    public void updateInventory() {
        List<LgSalesPromotionActivity> allActivity = orderDao.getAllActivity();
        for(LgSalesPromotionActivity activity : allActivity){
            Long size = redisTemplate.opsForList().size("activity" + activity.getActivityId());
            orderDao.updateInventory(size,activity.getActivityId());
        }

//        ListOperations opsForList = redisTemplate.opsForList();
//        long size1 = opsForList.size("activity"+lgTourOrder.getProductId());
//        for(long i = 0; i < size1; i++){
//            opsForList.rightPop("activity"+lgTourOrder.getProductId());
//        }
//        LgSalesPromotionActivity activity = orderDao.getActivityById((int) lgTourOrder.getProductId());
//        for(int i = 1; i <= activity.getInventory(); i++){
//            opsForList.leftPush("activity"+activity.getActivityId(),i);
//        }
    }

    @Override
    public List<LgTourOrder> getAllOrder(LgTourOrder lgTourOrder) {
        return orderDao.getAllOrder(lgTourOrder);
    }

    @Override
    public List<LgTourOrder> getNotPayOrder(int userId) {
        return orderDao.getNotPayOrder(userId);
    }

    @Override
    public List<LgTourOrder> getPayOrder(int userId) {
        return orderDao.getPayOrder(userId);
    }

    @Override
    public List<LgTourOrder> getAllOrderById(int userId) {
        return orderDao.getAllOrderById(userId);
    }

    @Override
    public List<LgTourOrder> getNoCommentOrder(int userId) {
        return orderDao.getNoCommentOrder(userId);
    }

    @Override
    public List<LgGroup> findGroup(String groupName) {
        return orderDao.findGroup(groupName);
    }


}
