package com.zyc.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyc.cloud.dto.EditNodesDto;
import com.zyc.cloud.service.EditService;
import com.zyc.cloud.service.impl.EditServiceImpl;
import com.zyc.cloud.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 * 处理技能树编辑请求
 */
@RestController
@RequestMapping
public class EditController {

    @Resource
    private EditService editService;


    @ApiOperation(value = "显示用户技能树节点")
    @GetMapping(value = "/edit/nodes")
    public String getSideAccounts(@RequestParam("user") String user) {
        List<EditNodesDto> skillNodes = editService.getNodes(user);
        JSONObject jsonData = new JSONObject();
        jsonData.put("skillNodes", skillNodes);

        return JsonResult.jsonWithRecord(jsonData);
    }
}
