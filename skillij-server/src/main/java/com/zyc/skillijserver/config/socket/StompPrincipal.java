package com.zyc.skillijserver.config.socket;

import java.security.Principal;

/**
 * Created on 2018/7/17.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class StompPrincipal implements Principal {
    private String user;

    public StompPrincipal(String user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return this.user;
    }
}
