package com.zyc.skillijserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zyc.skillijcommon.constant.Constant;
import com.zyc.skillijcommon.utils.JsonResult;
import com.zyc.skillijserver.dto.RedirectMessageDto;
import com.zyc.skillijserver.dto.UserChatsDto;
import com.zyc.skillijserver.service.ChatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 获取用户聊天记录
     * @param username
     * @return
     */
    @ApiOperation(value = "获取聊天记录")
    @GetMapping(value = "/get-chats")
    public String getSideAccounts(@RequestParam("username") String username) {
        UserChatsDto userChatsDto = chatService.getChats(username);
        JSONObject jsonData = new JSONObject();
        jsonData.put("chats", userChatsDto);

        return JsonResult.jsonWithRecord(jsonData);
    }

    /**
     * 新建聊天
     * @param fromUser
     * @param toUser
     * @return
     */
    @ApiOperation(value = "新建聊天")
    @PostMapping(value = "/new-chat")
    public String addChat(@RequestParam("fromUser") String fromUser,
                          @RequestParam("toUser") String toUser) {
        String addChatResult = chatService.addChat(fromUser, toUser);
        JSONObject jsonData = new JSONObject();
        jsonData.put("addChatResult", addChatResult);

        return JsonResult.jsonWithRecord(jsonData);
    }

    /**
     * 点对点聊天
     * @param MsgJson
     */
    @MessageMapping("/chat")//websocket的映射
    public void chat(Principal principal, String MsgJson){
        //解析客户端发送的消息
        RedirectMessageDto redirectMessageDto = JSON.parseObject(MsgJson, new TypeReference<RedirectMessageDto>(){});

        //将消息添加到发信人和收信人的聊天记录
        chatService.saveBothRecord(redirectMessageDto);

        //发送消息给订阅 "/user/topic/chat" 且用户名为receiver的用户
        simpMessagingTemplate.convertAndSendToUser(redirectMessageDto.getReceiver(), "/topic/chat", MsgJson);
    }
}
