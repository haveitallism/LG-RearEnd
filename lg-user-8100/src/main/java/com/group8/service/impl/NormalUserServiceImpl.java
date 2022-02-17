package com.group8.service.impl;

import com.group8.dao.NormalUserDao;
import com.group8.entity.LgNormalUser;
import com.group8.service.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author laiyong
 * @date 2022/2/17 11:31 星期四
 * @apiNote
 */
@Service
public class NormalUserServiceImpl implements NormalUserService {

    @Autowired
    NormalUserDao normalUserDao;

    @Override
    public boolean checkActiveCode(String code) {
        //验证激活码获取此用户信息
        LgNormalUser lgNormalUser = normalUserDao.checkActiveCode(code);
        System.out.println(code);
        System.out.println(lgNormalUser);
        //如果验证激活码成功修改状态
        if(lgNormalUser != null){
            normalUserDao.updateUserStatus(lgNormalUser.getUserId());
        }
        return lgNormalUser != null;
    }


}
