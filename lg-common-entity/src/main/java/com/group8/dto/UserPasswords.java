package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author QY
 * @create 2022-02-23 17:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswords {

    private int userId;
    private String oldPassword;
    private String newPassword;

}
