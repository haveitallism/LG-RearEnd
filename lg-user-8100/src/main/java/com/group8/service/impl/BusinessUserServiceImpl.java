package com.group8.service.impl;

import com.group8.dao.BusinessUserDao;
import com.group8.entity.LgBussinessUser;
import com.group8.service.BusinessUserService;
import com.group8.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author QY
 * @create 2022-02-17 19:14
 */

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    @Autowired
    BusinessUserDao businessUserDao;

    /**
     * 用户注册
     * @param lgBussinessUser
     * @return
     */
    @Override
    public int addNormalUser(LgBussinessUser lgBussinessUser) {
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
        return businessUserDao.addBuss(lgBussinessUser);
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
    public List<LgBussinessUser> findAll() {
        return businessUserDao.findAll();
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
