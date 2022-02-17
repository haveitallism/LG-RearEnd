package com.group8.service;

import com.group8.entity.LgNormalUser;

/**
 * @author laiyong
 * @date 2022/2/17 11:31 星期四
 * @apiNote
 */
public interface NormalUserService {
     //验证激活码
     boolean checkActiveCode(String code);


}
