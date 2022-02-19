package com.group8.dao;

import com.group8.entity.LgBussinessUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author QY
 * @create 2022-02-17 19:20
 */

public interface BusinessUserDao {

    int addBuss(LgBussinessUser lgBussinessUser);

    LgBussinessUser findByUsernameAndPwd(@Param("userName") String userName,@Param("encryptedPwd") String encryptedPwd);

    LgBussinessUser findById(int id);

    int update(LgBussinessUser lgBussinessUser);

    List<LgBussinessUser> findAll();
}
