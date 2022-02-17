package com.group8.service;

import com.group8.entity.LgNormalUser;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/17 11:31 星期四
 * @apiNote
 */
public interface NormalUserService {
    int addNormalUser(LgNormalUser lgNormalUser);

    LgNormalUser findByUsernameAndPwd(String userName, String password);

    List<LgNormalUser> findAll();

    LgNormalUser findById(int id);

    int update(LgNormalUser lgNormalUser);
}
