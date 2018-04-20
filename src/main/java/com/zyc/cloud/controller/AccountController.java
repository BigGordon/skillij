package com.zyc.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyc.cloud.dto.SkillTreeDto;
import com.zyc.cloud.repository.SkillRepository;
import com.zyc.cloud.service.AccountService;
import com.zyc.cloud.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
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

        if (StringUtils.isEmpty(user)) {
            return JsonResult.jsonWithErrMsg("未填写用户名");
        }

        if (StringUtils.isEmpty(passwd)) {
            return JsonResult.jsonWithErrMsg("未填写密码");
        }

        String loginResult = accountService.getLoginResult(user, passwd);
        JSONObject jsonData = new JSONObject();
        jsonData.put("loginResult", loginResult);

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
}
