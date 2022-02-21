package com.group8.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.group8.dao.NormalUserDao;
import com.group8.dto.UserCollects;
import com.group8.dto.UserLoginForm;
import com.group8.dto.UploadImg;
import com.group8.entity.LgGroup;
import com.group8.entity.LgNormalUser;
import com.group8.entity.LgNormalUserGroupCollect;
import com.group8.entity.LgNormalUserScenicspotCollect;
import com.group8.entity.LgNormalUserTravelnotesCollect;
import com.group8.entity.LgScenicspot;
import com.group8.entity.LgTravelnotes;
import com.group8.service.NormalUserService;
import com.group8.utils.JWTUtils;
import com.group8.utils.MD5Utils;
import com.group8.utils.QiuniuUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;
import java.util.*;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public LgNormalUser checkUserName(String userName) {
        return normalUserDao.checkUserName(userName);
    }

    @Override
    public int addNormalUser(LgNormalUser lgNormalUser) {
        // 密码使用MD5加密,重新设置回去
        String encryptedPwd = MD5Utils.encrypt(lgNormalUser.getUserPassword(), lgNormalUser.getUserName() + "lg");
        lgNormalUser.setUserPassword(encryptedPwd);
        // 获取当前时间为注册时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgNormalUser.setCreatedTime(timestamp);
        // 设置用户默认头像,用户后期自行修改
        String defaultAvatar = "https://gitee.com/cdlycode/oss/raw/master/uPic/2022-02/17-145600.jpeg";
        lgNormalUser.setUserHeadImg(defaultAvatar);
        // 设置账号状态为'0'，用户前往邮件激活
        lgNormalUser.setUserStatus("0");
        // 发送验证邮件
        // 生成随机验证码
        UUID uuid = UUID.randomUUID();
        String activeCode = uuid.toString().replace("-", "");
        lgNormalUser.setActiveCode(activeCode);
        // 将激活码存入redis，设置失效时间为30分钟
        redisTemplate.opsForValue().set(activeCode, activeCode);
        redisTemplate.expire(activeCode, 30, TimeUnit.MINUTES);
        rabbitTemplate.convertAndSend("LG-mail-exchange", "LgMail", lgNormalUser);
        return normalUserDao.addNormalUser(lgNormalUser);
    }

    @Override
    public String login(LgNormalUser lgNormalUser) {
        // 密码加密后查询
        String encryptedPwd = MD5Utils.encrypt(lgNormalUser.getUserPassword(), lgNormalUser.getUserName() + "lg");
        LgNormalUser normalUser = normalUserDao.findByUsernameAndPwd(lgNormalUser.getUserName(), encryptedPwd);
        if (normalUser != null) {
            String token = JWTUtils.sign(normalUser.getUserName(), normalUser.getUserPassword());
            redisTemplate.opsForValue().set(normalUser.getUserName(), token);
            return token;
        } else {
            return null;
        }
    }

    @Override
    public LgNormalUser getInfo(String token) {
        String userName = JWTUtils.getUserName(token);
        String encryptedPwd = JWTUtils.getPassword(token);
        return normalUserDao.findByUsernameAndPwd(userName, encryptedPwd);
    }

    @Override
    public int updateHeadImg(UploadImg uploadImg) {
        String url = QiuniuUtils.uploadPicture(uploadImg);
        System.out.println(url);
        return normalUserDao.updateHeadImg(uploadImg.getId(), url);
    }

    @Override
    public boolean logout(String token) {
        // 删除redis中的token
        String userName = JWTUtils.getUserName(token);
        return redisTemplate.delete(userName);
    }

    @Override
    public List<LgNormalUser> findByCondition(LgNormalUser lgNormalUser) {
        return normalUserDao.findByCondition(lgNormalUser);
    }

    @Override
    public LgNormalUser findById(int id) {
        return normalUserDao.findById(id);
    }

    @Override
    public int update(LgNormalUser lgNormalUser) {
        // 密码使用MD5加密,重新设置回去
        String encryptedPwd = MD5Utils.encrypt(lgNormalUser.getUserPassword(), lgNormalUser.getUserName() + "lg");
        lgNormalUser.setUserPassword(encryptedPwd);
        // 获取当前时间为更新时间
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        lgNormalUser.setUpdatedTime(timestamp);
        return normalUserDao.update(lgNormalUser);
    }

    @Override
    public boolean checkActiveCode(String code) {
        if (redisTemplate.hasKey(code)) {
            //验证激活码获取此用户信息
            LgNormalUser lgNormalUser = normalUserDao.checkActiveCode(code);
            //如果验证激活码成功修改状态
            if (lgNormalUser != null) {
                normalUserDao.updateUserStatus(lgNormalUser.getUserId());
                redisTemplate.delete(code);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int deleteById(int id) {
        return normalUserDao.deleteById(id);
    }

    @Override
    public void browse(long userId, Object browsed) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Boolean add = zSetOperations.add(Long.toString(userId), browsed, System.currentTimeMillis());
    }

    @Override
    public List<Object> selectBrowsed(long userId) {
        if (userId <= 0) {
            return Collections.emptyList();
        }
        // 获取用户最近浏览的项目
        Set<Object> set = redisTemplate.opsForZSet().reverseRange(Long.toString(userId), 0, 7);
        List<Object> arrayList = new ArrayList<>();
        set.forEach(item -> {
            arrayList.add(item);
            if (item instanceof LgGroup) {
                System.out.println("LgGroup");
            } else if (item instanceof LgTravelnotes) {
                System.out.println("LgTravelnotes");
            } else if (item instanceof LgScenicspot) {
                System.out.println("LgScenicspot");
            }
        });
        return arrayList;
    }
    /**
     * 收藏游记
     * @param notesCollect
     * @return
     */
    @Override
    public int addTravelCollect(LgNormalUserTravelnotesCollect notesCollect) {
        long currentTimeMillis = System.currentTimeMillis();
        //把收藏内容存在Redis里面
        ZSetOperations<String,String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add("Collects-" + notesCollect.getUserId(),"notesId:"+ notesCollect.getNotesId(),currentTimeMillis);
        //设置收藏时间
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        notesCollect.setCollectTime(timestamp);
        return normalUserDao.addTravelCollect(notesCollect);
    }

    /**
     * 收藏团游
     * @param groupCollect
     * @return
     */
    @Override
    public int addGroupCollect(LgNormalUserGroupCollect groupCollect) {
        //设置收藏时间
        long currentTimeMillis = System.currentTimeMillis();
        //把收藏内容存在Redis里面
        ZSetOperations<String,String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add("Collects-" + groupCollect.getUserId(),"groupId:"+ groupCollect.getGroupId(),currentTimeMillis);
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        groupCollect.setCollectTime(timestamp);
        return normalUserDao.addGroupCollect(groupCollect);
    }

    /**
     * 收藏景点攻略
     * @param
     * @return
     */
    @Override
    public int addScenicCollect(LgNormalUserScenicspotCollect scenicCollect) {
        //设置收藏时间
        long currentTimeMillis = System.currentTimeMillis();
        //把收藏内容存在Redis里面
        ZSetOperations<String,String> opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add("Collects-" + scenicCollect.getUserId(),"scenicId:"+ scenicCollect.getScenicId(),currentTimeMillis);
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        scenicCollect.setCollectTime(timestamp);
        return normalUserDao.addScenicCollect(scenicCollect);
    }

    /**
     * 查询用户所有收藏项目
     * @return
     */
    @Override
    public List<UserCollects> showAllCollects(int userId) {
        ZSetOperations<String,Object> opsForZSet = redisTemplate.opsForZSet();
        //收藏时间越新的先排列出来
        Set<Object> set = opsForZSet.reverseRange("Collects-" + userId, 0, 9);
        List<UserCollects> collectsList = new ArrayList<>();
        for (Object typeName:set) {
            //得到xxxId：groupId
            String type = typeName.toString();
            //得到项目id
            String[] split = type.split(":");
            String groupId = split[1];
            if(StrUtil.contains(type,"scenicId")){

                collectsList.add(new UserCollects(Integer.parseInt(groupId),null,"scenic"));
            }else if(StrUtil.contains(type,"notesId")) {
                collectsList.add(new UserCollects(Integer.parseInt(groupId),null,"notes"));
            }else {
                collectsList.add(new UserCollects(Integer.parseInt(groupId),null,"group"));
            }
        }
            //设置放入sql的list
            System.out.println("新集合" + collectsList);
            //List<UserCollects> collectsList  = normalUserDao.findUserCollects(set);
            //System.out.println(collectsList);
            //查询该用户所有的游记
            //List<LgNormalUserTravelnotesCollect> travelCollects = normalUserDao.findTravelCollects(userId);
            //查询该用户收藏的所有团游项目
            //List<LgNormalUserGroupCollect> groupCollects = normalUserDao.findgroupCollects(userId);
            //查询该用户收藏的所有景点攻略
            //List<LgNormalUserScenicspotCollect> scenicCollects = normalUserDao.findscenicCollects(userId);
            return null;
        }

}
