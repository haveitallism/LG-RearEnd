package com.group8.service.impl;

import com.group8.dao.OrderDao;
import com.group8.entity.LgTourOrder;
import com.group8.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    OrderDao orderDao;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public String some(){
        String str="";
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        if(!redisTemplate.hasKey("str")){
            System.out.println("查询数据库");
            str="aaa";
            opsForValue.set("str",str);
        }else{
            System.out.println("查询缓存");
            str=(String) opsForValue.get("str");
        }
        return str;
    }

    @Override
    public synchronized void incr(){
        RLock mylock = redissonClient.getLock("mylock");
        mylock.lock();
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        Object num = opsForValue.get("num");
        if(num == null){
            opsForValue.set("num", "1");
        }else{
            int count = Integer.parseInt(num.toString());
            count++;
            opsForValue.set("num", count+"");
        }
        mylock.unlock();
    }

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
    public void second(){
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        Object item = opsForList.rightPop("list");
        if(item == null){
            System.out.println("over");
        }else {
            LgTourOrder lgTourOrder = new LgTourOrder();
            lgTourOrder.setProductId(1);
            lgTourOrder.setOrderChoose("冰火两重天");
            lgTourOrder.setOrderPayoutStatus(0);
            orderDao.addOrder(lgTourOrder);
            System.out.println("ok:"+item);
        }
    }




}
