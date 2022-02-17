package com.group8.entity;

import org.apache.shiro.authc.AuthenticationToken;

public class JsonWebToken implements AuthenticationToken {

    private String token;

    public JsonWebToken() {
    }

    public JsonWebToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取用户名 token
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 获取密码 token
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
