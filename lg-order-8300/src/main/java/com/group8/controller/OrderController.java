package com.group8.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.group8.entity.LgTourOrder;
import com.group8.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
//@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/some")
    public String some(){
        String str = orderService.some();
        return str;
    }

    @RequestMapping("/incr")
    public String incr(){
        orderService.incr();
        return "ok";
    }

    @RequestMapping("/start")
    public String start(){
        orderService.start();
        return "ok";
    }

    @RequestMapping("/second")
    public String second(){
        orderService.second();
        return "ok";
    }

    @GetMapping("/get")
    public String getOrder(){
        orderService.getOrder();
        return "ok";
    }
}
