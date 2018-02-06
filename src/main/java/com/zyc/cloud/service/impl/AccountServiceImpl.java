package com.zyc.cloud.service.impl;

import com.zyc.cloud.repository.AccountRepository;
import com.zyc.cloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    /**
     * 校验用户登录
     * @param user
     * @param passwd
     * @return
     */
    @Override
    public String getLoginResult(String user, String passwd) {
        String realPasswd = accountRepository.findPasswdByUser(user);
        if (realPasswd == null) {
            return "用户名不存在";
        }
        if (passwd.equals(realPasswd)) {
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

}
