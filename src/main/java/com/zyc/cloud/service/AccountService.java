package com.zyc.cloud.service;

import com.zyc.cloud.domain.SkillijUser;
import com.zyc.cloud.dto.SkillTreeDto;

import java.util.List;

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

    /**
     * 获取账号用于侧边栏显示
     * @return
     */
    List<String> getSideAccounts();

    /**
     * 获取用户的技能树
     * @param user
     * @return
     */
    SkillTreeDto getUserSkillTree(String user);

    /**
     * 根据用户名找到用户对象
     * @param username
     * @return
     */
    SkillijUser findByUsername(String username);

    /**
     * 检验token有效性
     * @param token
     * @return
     */
    Boolean getTokenValidity(String token);

    /**
     * 修改密码，返回修改结果
     * @param username
     * @param oldPasswd
     * @param newPasswd
     * @return
     */
    String changePassword(String username, String oldPasswd, String newPasswd);
    /**
     * 用户注册信息录入
     * @param mail
     * @param userName
     * @param passwd
     * @return
     */
    String getRegisterResult(String mail, String userName, String passwd);

}
