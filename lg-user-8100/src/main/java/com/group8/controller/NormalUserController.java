package com.group8.controller;

import com.group8.entity.LgNormalUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.NormalUserService;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyong
 * @date 2022/2/17 10:49 星期四
 * @apiNote 普通用户Controller
 */
@Api(tags = "普通用户管理") //
@RestController
@RequestMapping("/nUser")
public class NormalUserController {

    @Autowired
    NormalUserService normalUserService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public ResponseEntity<String> register(@RequestBody LgNormalUser lgNormalUser){
        return null;
    }

    @RequestMapping("/sendEmail")
    public String sendEmail(){
        LgNormalUser user = new LgNormalUser();
        user.setUserEmail("shqyshqy123@163.com");
        user.setActiveCode("12345");
        rabbitTemplate.convertAndSend("LG-mail-exchange","LgMail",user);
        return "发送邮件成功";
    }

    @RequestMapping("/activeUser")
    public String checkActiveCode(String code){
        boolean b = normalUserService.checkActiveCode(code);
        if(b){
            return "验证成功";
        }else {
            return "验证失败";
        }
    }





}
