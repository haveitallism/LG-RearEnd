package com.group8.service;

import com.group8.dto.*;
import com.group8.entity.LgNormalUser;
import com.group8.entity.LgNormalUserGroupCollect;
import com.group8.entity.LgNormalUserScenicspotCollect;
import com.group8.entity.LgNormalUserTravelnotesCollect;

import java.util.List;


/**
 * @author laiyong
 * @date 2022/2/17 11:31 星期四
 * @apiNote
 */
public interface NormalUserService {

    int addNormalUser(LgNormalUser lgNormalUser);

    List<LgNormalUser> findByCondition(LgNormalUser lgNormalUser);

    int update(LgNormalUser lgNormalUser);

    int update(UserPasswords userPasswords);
    //验证激活码
    boolean checkActiveCode(String code);

    int deleteById(int id);

    LgNormalUser login(UserLoginForm userLoginForm);

    int addTravelCollect(LgNormalUserTravelnotesCollect notesCollect);

    int addGroupCollect(LgNormalUserGroupCollect groupCollect);

    int addScenicCollect(LgNormalUserScenicspotCollect parseObject);

    List<UserCollects> showAllCollects(int userId);

    boolean logout(String token);

    void browse(long userId, Object browsed);

    List<Object> selectBrowsed(long userId);

    LgNormalUser getInfo(String token);

    String updateHeadImg(UploadImg uploadImg);

    LgNormalUser findById(int id);

    LgNormalUser checkUserName(String userName);

    List<UserCollects> showTypesCollects(UserCollects userCollects);
}
