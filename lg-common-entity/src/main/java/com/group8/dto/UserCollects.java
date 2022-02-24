package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

/**
 * @author QY
 * @create 2022-02-19 21:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollects {

    private int projectId;
    private String projectName;
    private String typeName;

}
