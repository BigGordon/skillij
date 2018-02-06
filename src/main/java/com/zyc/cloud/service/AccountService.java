package com.zyc.cloud.service;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface AccountService {

    /**
     * 校验用户登录
     * @param user
     * @param passwd
     * @return
     */
    String getLoginResult(String user, String passwd);

}
