package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author laiyong
 * @date 2022/2/17 15:26 星期四
 * @apiNote 用户登陆信息dto类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginForm {

    private String userName;
    private String password;
    //private String verifyCode;

}
