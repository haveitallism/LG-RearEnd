package com.group8.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTUtils {
    /**
     * 过期时间 5min过期 后期要换掉 学完redis 就不用过期token了
     */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
    private static final String SECRIT = "StepFour";//如果别人想要解密我的JWT，必须有这个秘钥

    /**
     * @param token    生成的JWT
     * @param username 账号
     * @param password 密码
     * @return
     * @todo 这个验证方法是再生成一次秘钥，自动比较 当前token是不是之前生成的token
     */
    public static boolean verify(String token, String username,
                                 String password) {
        //这里需要加密算法来对我们的woniuxy进行加密
        Algorithm algorithm = Algorithm.HMAC256(SECRIT);
        //重新生成一个token
        JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).withClaim("password", password)
                .build();
        //比较两个token的内容
        verifier.verify(token);
        return true;
    }

    /**
     * 签名生成jwt
     *
     * @param username
     * @param password
     * @return token
     */
    public static String sign(String username, String password) {
        //过期时间
        Date exprietime = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //对唯一秘钥进行加密使用
        Algorithm algorithm = Algorithm.HMAC256(SECRIT);
        return JWT.create()
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(exprietime)
                .sign(algorithm);//签名 如果签名不对，则无法解析jwt
    }

    /**
     * 用来传入token返回一个该token的用户名
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Claim username = jwt.getClaim("username");
        return username.asString();
    }

    /**
     * 用来传入token返回一个该token的密码
     *
     * @param token
     * @return
     */
    public static String getPassword(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Claim password = jwt.getClaim("password");
        return password.asString();
    }
}
