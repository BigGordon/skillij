package com.zyc.cloud.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created on 2018/6/7.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
