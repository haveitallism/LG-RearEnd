package com.group8.service;

import com.group8.dto.UploadImg;
import com.group8.entity.LgManageUser;
import com.group8.entity.LgNormalUser;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/20 16:09 星期日
 * @apiNote
 */
public interface ManageUserService {

    LgManageUser checkUserName(String userName);

    int addManageUser(LgManageUser lgManageUser);

    String login(LgManageUser lgManageUser);

    LgManageUser getInfo(String token);

    boolean logout(String token);

    List<LgManageUser> findByCondition(LgManageUser lgManageUser);

    LgManageUser findById(int id);

    int updateHeadImg(UploadImg uploadImg);

    int deleteById(int id);
}
