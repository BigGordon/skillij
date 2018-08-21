package com.zyc.skillijserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyc.skillijcommon.constant.Constant;
import com.zyc.skillijserver.dto.EditNodesDto;
import com.zyc.skillijserver.dto.EditTreeTitleDto;
import com.zyc.skillijserver.service.EditService;
import com.zyc.skillijcommon.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/4/18.
 * Author: Gordon
 * Email: biggordon@163.com
 * 处理技能树编辑请求
 */
@RestController
@RequestMapping(value = "/edit")
public class EditController {

    @Resource
    private EditService editService;

    @ApiOperation(value = "修改技能树")
    @PostMapping(value = "/editTree")
    public String getEditTree(@RequestParam("treeId") Long treeId,
                              @RequestParam("userName") String userName,
                              @RequestParam("newTreeName") String newTreeName) {
        String editTreeResult = editService.editTree(newTreeName, userName, treeId);
        JSONObject jsonEditTree = new JSONObject();
        jsonEditTree.put("editTreeResult", editTreeResult);

        return  JsonResult.jsonWithRecord(jsonEditTree);
    }


    @ApiOperation(value = "删除技能树")
    @PostMapping(value = "/delTree")
    public String getDelTree(@RequestParam("treeId") Long treeId) {
        editService.deleteTreeByTreeId(treeId);
        return JsonResult.jsonWithSuccess();
    }


    @ApiOperation(value = "添加新技能树")
    @PostMapping(value = "/newTree")
    public String getNewTree(@RequestParam("newTreeName") String newTreeName,
                             @RequestParam("user") String user) {
        String newTreeResult = editService.newTree(newTreeName, user);
        JSONObject jsonNewTree = new JSONObject();
        jsonNewTree.put("newTreeResult", newTreeResult);

        return  JsonResult.jsonWithRecord(jsonNewTree);
    }


    @ApiOperation(value = "显示技能树标题栏树名列表")
    @GetMapping(value = "/titles")
    public String getTreeTitle(@RequestParam("user") String user) {
        List<EditTreeTitleDto> treeTitles = editService.getTitles(user);
        JSONObject jsonTree = new JSONObject();
        jsonTree.put("treeTitles", treeTitles);

        return JsonResult.jsonWithRecord(jsonTree);

    }


    @ApiOperation(value = "显示用户技能树节点")
    @GetMapping(value = "/nodes")
    public String getNodes(@RequestParam("user") String user,
                           @RequestParam("treeId") Long treeId) {
        List<EditNodesDto> skillNodes = editService.getNodes(user, treeId);
        JSONObject jsonData = new JSONObject();
        jsonData.put("skillNodes", skillNodes);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "修改用户技能树节点")
    @PostMapping(value = "/revise")
    public String reviseNodes(@RequestParam("nodes") String nodes,
                              @RequestParam("user") String username,
                              @RequestParam("treeId") Long treeId) {
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
        String result = editService.reviseSkill(editNodesList, username, treeId);

        JSONObject jsonData = new JSONObject();
        jsonData.put("result", result);

        return JsonResult.jsonWithRecord(jsonData);
    }

    @ApiOperation(value = "删除用户技能树节点")
    @PostMapping(value = "/delete")
    public String deleteNodes(@RequestParam("ids") String skillIds) {
        List<Long> idList = JSON.parseArray(skillIds, Long.class);
        editService.deleteSkillByIds(idList);
        return JsonResult.jsonWithSuccess();
    }

    @ApiOperation(value = "新建用户技能树节点")
    @PostMapping(value = "/new")
    public String newNode(@RequestParam("nodes") String nodes,
                          @RequestParam("user") String username,
                          @RequestParam("treeId") Long treeId) {
        JSONObject jsonObject = JSON.parseObject(nodes);
        EditNodesDto newNode = new EditNodesDto();
        newNode.setSkillName(jsonObject.getString("skillName"));
        newNode.setParentSkillName(jsonObject.getString("parentSkillName"));
        newNode.setProficiency(jsonObject.getInteger("proficiency"));
        newNode.setSkillDescrip(jsonObject.getString("description"));
        String result = editService.newSkill(newNode, username, treeId);

        JSONObject jsonData = new JSONObject();
        jsonData.put("result", result);

        return JsonResult.jsonWithRecord(jsonData);
    }
}
