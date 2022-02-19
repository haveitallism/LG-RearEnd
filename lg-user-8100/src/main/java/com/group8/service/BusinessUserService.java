package com.group8.service;

import com.group8.entity.LgBussinessUser;

import java.util.List;

/**
 * @author QY
 * @create 2022-02-17 19:12
 */

public interface BusinessUserService {

    int addNormalUser(LgBussinessUser lgBussinessUser) ;

    LgBussinessUser login(String userName, String password);

    LgBussinessUser findById(int id);

    int update(LgBussinessUser lgBussinessUser);

    List<LgBussinessUser> findAll();
}
