package com.zyc.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyc.cloud.dto.EditNodesDto;
import com.zyc.cloud.service.EditService;
import com.zyc.cloud.service.impl.EditServiceImpl;
import com.zyc.cloud.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public String getNodes(@RequestParam("user") String user) {
        List<EditNodesDto> skillNodes = editService.getNodes(user);
        JSONObject jsonData = new JSONObject();
        jsonData.put("skillNodes", skillNodes);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "修改用户技能树节点")
    @PostMapping(value = "/edit/revise")
    public String reviseNodes(@RequestParam("nodes") String nodes) {
        JSONArray jsonArray = JSON.parseArray(nodes);
        List<EditNodesDto> editNodesList = new ArrayList<>();
        //将jsonObjective转化为SkillTreeDto对象
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            EditNodesDto editNode = new EditNodesDto();
            editNode.setSkillId(jsonObject.getLong("skillId"));
            editNode.setSkillName(jsonObject.getString("skillName"));
            editNode.setParentSkillName(jsonObject.getString("parentSkillName"));
            editNode.setProficiency(jsonObject.getInteger("proficiency"));
            editNode.setSkillDescrip(jsonObject.getString("description"));
            editNodesList.add(editNode);
        }
        String result = editService.reviseSkill(editNodesList, "gordon");//TODO: 用户名要根据用户不同更改

        JSONObject jsonData = new JSONObject();
        jsonData.put("result", result);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "删除用户技能树节点")
    @PostMapping(value = "/edit/delete")
    public String deleteNodes(@RequestParam("ids") String skillIds) {
        List<Long> idList = JSON.parseArray(skillIds, Long.class);
        editService.deleteSkillByIds(idList);
        return JsonResult.jsonWithSuccess();
    }

    @ApiOperation(value = "新建用户技能树节点")
    @PostMapping(value = "/edit/new")
    public String newNode(@RequestParam("nodes") String nodes) {
        JSONObject jsonObject = JSON.parseObject(nodes);
        EditNodesDto newNode = new EditNodesDto();
        newNode.setSkillName(jsonObject.getString("skillName"));
        newNode.setParentSkillName(jsonObject.getString("parentSkillName"));
        newNode.setProficiency(jsonObject.getInteger("proficiency"));
        newNode.setSkillDescrip(jsonObject.getString("description"));
        String result = editService.newSkill(newNode, "gordon");//TODO: 用户名要根据用户不同更改

        JSONObject jsonData = new JSONObject();
        jsonData.put("result", result);

        return JsonResult.jsonWithRecord(jsonData);
    }
}
