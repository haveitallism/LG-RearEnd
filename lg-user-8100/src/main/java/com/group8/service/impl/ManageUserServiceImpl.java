package com.group8.service.impl;

import com.group8.dao.ManageUserDao;
import com.group8.dao.NormalUserDao;
import com.group8.dto.UploadImg;
import com.group8.entity.LgManageUser;
import com.group8.entity.LgNormalUser;
import com.group8.service.ManageUserService;
import com.group8.utils.JWTUtils;
import com.group8.utils.MD5Utils;
import com.group8.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author laiyong
 * @date 2022/2/20 16:24 星期日
 * @apiNote
 */
@Service
public class ManageUserServiceImpl implements ManageUserService {

    @Autowired(required = false)
    ManageUserDao manageUserDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public LgManageUser checkUserName(String userName) {
        return manageUserDao.checkUserName(userName);
    }

    @Override
    public int addManageUser(LgManageUser lgManageUser) {
        // 密码使用MD5加密,重新设置回去
        String encryptedPwd = MD5Utils.encrypt(lgManageUser.getUserPassword(), lgManageUser.getUserName() + "lg");
        lgManageUser.setUserPassword(encryptedPwd);
        // 获取当前时间为注册时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgManageUser.setCreatedTime(timestamp);
        // 设置用户默认头像,用户后期自行修改
        String defaultAvatar = "https://gitee.com/cdlycode/oss/raw/master/uPic/2022-02/17-145600.jpeg";
        lgManageUser.setUserHeadImg(defaultAvatar);
        // 设置账号状态为'0'，用户前往邮件激活
        lgManageUser.setUserStatus(0);
        // 设置账号角色为普通管理员
        lgManageUser.setUserRole("admin");
        return manageUserDao.addManageUser(lgManageUser);
    }

    @Override
    public String login(LgManageUser lgManageUser) {
        // 密码加密后查询
        String encryptedPwd = MD5Utils.encrypt(lgManageUser.getUserPassword(), lgManageUser.getUserName() + "lg");
        LgManageUser manageUser = manageUserDao.findByUsernameAndPwd(lgManageUser.getUserName(), encryptedPwd);
        if (manageUser != null) {
            String token = JWTUtils.sign(manageUser.getUserName(), manageUser.getUserPassword());
            redisTemplate.opsForValue().set(manageUser.getUserName(), token);
            return token;
        } else {
            return null;
        }
    }

    @Override
    public LgManageUser getInfo(String token) {
        String userName = JWTUtils.getUserName(token);
        String encryptedPwd = JWTUtils.getPassword(token);
        return manageUserDao.findByUsernameAndPwd(userName, encryptedPwd);
    }

    @Override
    public boolean logout(String token) {
        // 删除redis中的token
        String userName = JWTUtils.getUserName(token);
        return redisTemplate.delete(userName);
    }

    @Override
    public List<LgManageUser> findByCondition(LgManageUser lgManageUser) {
        return manageUserDao.findByCondition(lgManageUser);
    }

    @Override
    public LgManageUser findById(int id) {
        return manageUserDao.findById(id);
    }

    @Override
    public int updateHeadImg(UploadImg uploadImg) {
        String url = QiniuUtils.uploadFile(uploadImg);
        return manageUserDao.updateHeadImg(uploadImg.getId(), url);
    }

    @Override
    public int deleteById(int id) {
        return manageUserDao.deleteById(id);
    }
}
