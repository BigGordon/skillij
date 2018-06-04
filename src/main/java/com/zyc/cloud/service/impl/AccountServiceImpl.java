package com.zyc.cloud.service.impl;

import com.zyc.cloud.domain.SkillijUser;
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

    /**
     * 用户注册信息录入
     * @param mail
     * @param userName
     * @param passwd
     * @return
     */
    @Override
    public String getRegisterResult(String mail, String userName, String passwd) {
        SkillijUser existedMail = accountRepository.findSkillijUserByMail(mail);

        if(existedMail != null) {
            return "邮箱已被注册";
        }

        SkillijUser existedUserName = accountRepository.findSkillijUserByUserName(userName);

        if(existedUserName != null) {
            return "用户名已注册";
        }

        SkillijUser newSkillijUser = new SkillijUser();
        newSkillijUser.setUsername(userName);
        newSkillijUser.setMail(mail);
        newSkillijUser.setPassword(passwd);
        accountRepository.saveAndFlush(newSkillijUser);
        return "注册成功";
    }
}
