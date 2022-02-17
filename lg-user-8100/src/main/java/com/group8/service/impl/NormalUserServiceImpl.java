package com.group8.service.impl;

import com.group8.dao.NormalUserDao;
import com.group8.entity.LgNormalUser;
import com.group8.service.NormalUserService;
import com.group8.utils.MD5Utils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/17 11:31 星期四
 * @apiNote
 */
@Service
public class NormalUserServiceImpl implements NormalUserService {

    @Autowired(required = false)
    NormalUserDao normalUserDao;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public int addNormalUser(LgNormalUser lgNormalUser) {
        // 密码使用MD5加密,重新设置回去
        String encryptedPwd = MD5Utils.encrypt(lgNormalUser.getUserPassword(), lgNormalUser.getUserName() + "lg");
        lgNormalUser.setUserPassword(encryptedPwd);
        // 获取当前时间为注册时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgNormalUser.setCreatedTime(timestamp);
        // 设置用户账户状态为0，用户需要邮箱验证
        lgNormalUser.setUserStatus("0");
        // 设置用户默认头像,用户后期自行修改
        String defaultAvatar = "https://gitee.com/cdlycode/oss/raw/master/uPic/2022-02/17-145600.jpeg";
        lgNormalUser.setUserHeadImg(defaultAvatar);
        return normalUserDao.addNormalUser(lgNormalUser);
    }

    @Override
    public LgNormalUser findByUsernameAndPwd(String userName, String password) {
        // 密码加密后查询
        String encryptedPwd = MD5Utils.encrypt(password, userName + "lg");
        return normalUserDao.findByUsernameAndPwd(userName, encryptedPwd);
    }

    @Override
    public List<LgNormalUser> findAll() {
        return normalUserDao.findAll();
    }

    @Override
    public LgNormalUser findById(int id) {
        return normalUserDao.findById(id);
    }

    @Override
    public int update(LgNormalUser lgNormalUser) {
        // 获取当前时间为更新时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgNormalUser.setUpdatedTime(timestamp);
        return normalUserDao.update(lgNormalUser);
    }

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
