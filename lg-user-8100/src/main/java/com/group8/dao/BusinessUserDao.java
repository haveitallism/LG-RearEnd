package com.group8.dao;

import com.group8.entity.LgBussinessUser;
import com.group8.entity.LgNormalUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

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

    List<LgBussinessUser> findAll( LgBussinessUser lgBussinessUser);

    LgBussinessUser checkUserName(@Param("userName") String userName);

    //验证激活码
    LgBussinessUser checkActiveCode(String code);

    //修改用户状态
    void updateUserStatus(int userId);


}
