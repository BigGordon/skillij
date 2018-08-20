package com.zyc.skillijserver.dto;

import com.zyc.skillijcommon.dto.UserMessage;

import java.util.List;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class ChatSessionDto {

    /**
     * sessiong Id
     */
    private String id;

    /**
     * 用户的聊天对象
     */
    private UserPortraitDto user;

    /**
     * 所有的聊天信息
     */
    private List<UserMessage> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserPortraitDto getUser() {
        return user;
    }

    public void setUser(UserPortraitDto user) {
        this.user = user;
    }

    public List<UserMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<UserMessage> messages) {
        this.messages = messages;
    }
}
