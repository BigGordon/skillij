package com.zyc.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyc.cloud.dto.SkillTreeDto;
import com.zyc.cloud.repository.SkillRepository;
import com.zyc.cloud.service.AccountService;
import com.zyc.cloud.utils.JWTUtil;
import com.zyc.cloud.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2018/2/2.
 * Author: Gordon
 * Email: biggordon@163.com
 * 处理账号相关请求
 */
@RestController
@RequestMapping
public class AccountController {

    @Resource
    private AccountService accountService;

    @ApiOperation(value = "账号登录")
    @PostMapping(value = "/account/login")
    public String userLogin(@RequestParam("user") String user,
                            @RequestParam("passwd") String passwd) {

        String loginResult = accountService.getLoginResult(user, passwd);//使用shiro就不用自己判断了
        JSONObject jsonData = new JSONObject();
        jsonData.put("loginResult", loginResult);
        if (loginResult.equals("登录成功")) {
            jsonData.put("token", JWTUtil.sign(user, passwd));//放入JWT Token
        }

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "侧边账号显示")
    @GetMapping(value = "/account/get-side")
    public String getSideAccounts() {
        List<String> sideAccounts = accountService.getSideAccounts();
        JSONObject jsonData = new JSONObject();
        jsonData.put("sideAccounts", sideAccounts);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "获取用户技能")
    @GetMapping(value = "/account/skills")
    public String getSkills(@RequestParam("user") String user) {
        if (StringUtils.isEmpty(user)) {
            return JsonResult.jsonWithErrMsg("未填写用户名");
        }
        SkillTreeDto skillTree = accountService.getUserSkillTree(user);
        JSONObject jsonData = new JSONObject();
        jsonData.put("skills", skillTree);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "token有效性检查")
    @GetMapping(value = "/account/token")
    public String tokenCheck(@RequestParam("token") String token) {

        Boolean isTokenValid = accountService.getTokenValidity(token);
        JSONObject jsonData = new JSONObject();
        if (isTokenValid) {
            jsonData.put("token", token);
        } else {
            jsonData.put("token", "invalid");
        }

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "未授权")
    @GetMapping(value = "/account/unauth")
    public String unauth() {

        JSONObject jsonData = new JSONObject();
        jsonData.put("token", "invalid");

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/account/change-passwd")
    public String changePasswd(@RequestParam("username") String username,
                               @RequestParam("oldPasswd") String oldPasswd,
                               @RequestParam("newPasswd") String newPasswd) {

        String result = accountService.changePassword(username, oldPasswd, newPasswd);
        JSONObject jsonData = new JSONObject();
        jsonData.put("changeResult", result);

        return JsonResult.jsonWithRecord(jsonData);
    }
}
