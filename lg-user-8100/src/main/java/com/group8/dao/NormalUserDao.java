package com.group8.dao;

import com.group8.entity.LgNormalUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author QY
 * @create 2022-02-17 15:43
 */

public interface NormalUserDao {

    int addNormalUser(@Param("normalUser") LgNormalUser lgNormalUser);

    //验证激活码
    LgNormalUser checkActiveCode(String code);

    int updateUserStatus(int userId);
}
