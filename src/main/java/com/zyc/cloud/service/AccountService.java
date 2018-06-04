package com.zyc.cloud.service;

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
     * 用户注册信息录入
     * @param mail
     * @param userName
     * @param passwd
     * @return
     */
    String getRegisterResult(String mail, String userName, String passwd);

}
