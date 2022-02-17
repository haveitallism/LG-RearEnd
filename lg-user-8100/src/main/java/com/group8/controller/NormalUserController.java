package com.group8.controller;

import com.group8.entity.LgNormalUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.NormalUserService;
import io.swagger.annotations.Api;
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

    public ResponseEntity<String> register(@RequestBody LgNormalUser lgNormalUser){
        return null;
    }

}
