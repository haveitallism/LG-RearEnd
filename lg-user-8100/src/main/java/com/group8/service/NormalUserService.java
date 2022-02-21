package com.group8.service;

import com.group8.dto.UploadImg;
import com.group8.dto.UserLoginForm;
import com.group8.dto.UserQueryCondition;
import com.group8.entity.LgNormalUser;

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

    //验证激活码
    boolean checkActiveCode(String code);

    int deleteById(int id);

    String login(UserLoginForm userLoginForm);

    boolean logout(String token);

    void browse(long userId, Object browsed);

    List<Object> selectBrowsed(long userId);

    LgNormalUser getInfo(String token);

    int updateHeadImg(UploadImg uploadImg);

    LgNormalUser findById(int id);

    LgNormalUser checkUserName(String userName);
}
