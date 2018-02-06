package com.zyc.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyc.cloud.service.AccountService;
import com.zyc.cloud.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created on 2018/2/2.
 * Author: Gordon
 * Email: biggordon@163.com
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
}
