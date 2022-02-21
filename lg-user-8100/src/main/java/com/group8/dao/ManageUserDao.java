package com.group8.dao;

import com.group8.entity.LgManageUser;
import com.group8.entity.LgNormalUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author laiyong
 * @date 2022/2/20 16:25 星期日
 * @apiNote
 */
public interface ManageUserDao {

    LgManageUser checkUserName(@Param("userName") String userName);

    int addManageUser(@Param("manageUser") LgManageUser lgManageUser);

    LgManageUser findByUsernameAndPwd(@Param("userName") String userName, @Param("password")String encryptedPwd);

    List<LgManageUser> findByCondition(@Param("manageUser")LgManageUser lgManageUser);

    LgManageUser findById(@Param("id")int id);

    int updateHeadImg(@Param("id")int id, @Param("url")String url);

    int deleteById(@Param("id")int id);

}
