package com.group8.service.impl;

import com.group8.dao.BusinessUserDao;
import com.group8.entity.LgBussinessUser;
import com.group8.entity.LgNormalUser;
import com.group8.service.BusinessUserService;
import com.group8.utils.JWTUtils;
import com.group8.utils.MD5Utils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author QY
 * @create 2022-02-17 19:14
 */

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired(required = false)
    BusinessUserDao businessUserDao;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 用户注册
     * @param lgBussinessUser
     * @return
     */
    @Override
    public int addBusinessUser(LgBussinessUser lgBussinessUser) {
        // 密码使用MD5加密,重新设置回去
        String encryptedPwd = MD5Utils.encrypt(lgBussinessUser.getBussPassword(), lgBussinessUser.getBussPassword() + "lg");
        lgBussinessUser.setBussPassword(encryptedPwd);
        // 获取当前时间为注册时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgBussinessUser.setCreatedTime(timestamp);
        // 设置用户账户状态为0，用户需要邮箱验证
        lgBussinessUser.setBussStatus(0);
        // 设置用户默认头像,用户后期自行修改
        String defaultAvatar = "https://gitee.com/cdlycode/oss/raw/master/uPic/2022-02/17-145600.jpeg";
        lgBussinessUser.setBussHeadImg(defaultAvatar);
        // 发送验证邮件
        // 生成随机验证码
        UUID uuid = UUID.randomUUID();
        String activeCode = uuid.toString().replace("-", "");
        lgBussinessUser.setActiveCode(activeCode);
        // 将激活码存入redis，设置失效时间为30分钟
        redisTemplate.opsForValue().set(activeCode, activeCode);
        redisTemplate.expire(activeCode, 30, TimeUnit.MINUTES);
        rabbitTemplate.convertAndSend("LG-mail-exchange", "LgMail1", lgBussinessUser);
        return businessUserDao.addBuss(lgBussinessUser);
    }

    /**
     * 用户验证
     * @return
     */
    @Override
    public int checkName(String username) {
        //如果用户名重复
        if (businessUserDao.checkUserName(username) != null){
            return 0;
        }else {
            return 1;
        }

    }

    /**
     * 激活商铺
     * @param code
     * @return
     */
    @Override
    public boolean checkActiveCode(String code) {
        if (redisTemplate.hasKey(code)){
            //验证激活码获取此用户信息
            LgBussinessUser lgBussinessUser = businessUserDao.checkActiveCode(code);
            //如果验证激活码成功修改状态
            if (lgBussinessUser != null) {
                businessUserDao.updateUserStatus((int)lgBussinessUser.getBussId());
                redisTemplate.delete(code);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public LgBussinessUser getInfo(String token) {
        String userName = JWTUtils.getUserName(token);
        String encryptedPwd = JWTUtils.getPassword(token);
        return businessUserDao.findByUsernameAndPwd(userName, encryptedPwd);
    }

    @Override
    public boolean logout(String token) {
        // 删除redis中的token
        String userName = JWTUtils.getUserName(token);
        return redisTemplate.delete(userName);
    }

    /**
     * 登陆验证
     * @param userName
     * @param password
     * @return
     */
    @Override
    public LgBussinessUser login(String userName, String password) {
        // 密码加密后查询
        String encryptedPwd = MD5Utils.encrypt(password, password + "lg");
        return businessUserDao.findByUsernameAndPwd(userName, encryptedPwd);
    }

    /**
     * 查询所有商铺
     * @return
     */
    @Override
    public List<LgBussinessUser> findAll(LgBussinessUser lgBussinessUser) {
        //System.out.println(lgBussinessUser);
        return businessUserDao.findAll(lgBussinessUser);
    }



    /**
     * 根据id查询商铺用户
     * @param id
     * @return
     */
    @Override
    public LgBussinessUser findById(int id) {
        return businessUserDao.findById(id);
    }

    @Override
    public int update(LgBussinessUser lgBussinessUser) {
        //设置修改时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgBussinessUser.setUpdatedTime(timestamp);
        //设置修改的秘密
        String encryptedPwd = MD5Utils.encrypt(lgBussinessUser.getBussPassword(), lgBussinessUser.getBussPassword() + "lg");
        lgBussinessUser.setBussPassword(encryptedPwd);
        return businessUserDao.update(lgBussinessUser);
    }


}
