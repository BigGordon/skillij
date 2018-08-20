package com.zyc.skillijserver.service;

import com.zyc.skillijserver.dto.RedirectMessageDto;
import com.zyc.skillijserver.dto.UserChatsDto;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface ChatService {

    /**
     * 获取用户聊天记录
     * @param username
     * @return
     */
    UserChatsDto getChats(String username);

    /**
     * 发送新消息时存到双方数据库
     * @param redirectMessageDto
     */
    void saveBothRecord(RedirectMessageDto redirectMessageDto);

    /**
     * 新建对话
     * @param fromUserName
     * @param toUserName
     * @return
     */
    String addChat(String fromUserName, String toUserName);
}
