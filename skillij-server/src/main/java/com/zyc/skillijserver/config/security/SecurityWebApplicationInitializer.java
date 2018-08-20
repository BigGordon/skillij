package com.zyc.skillijserver.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created on 2018/7/27.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(WebSecurityConfig.class);
    }
}
