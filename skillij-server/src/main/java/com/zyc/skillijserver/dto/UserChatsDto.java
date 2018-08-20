package com.zyc.skillijserver.dto;

import java.util.List;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class UserChatsDto {

    /**
     * 当前用户，包含名字和头像路径
     */
    private UserPortraitDto user;

    /**
     * 该用户的对话
     */
    private List<ChatSessionDto> sessions;

    public UserPortraitDto getUser() {
        return user;
    }

    public void setUser(UserPortraitDto user) {
        this.user = user;
    }

    public List<ChatSessionDto> getSessions() {
        return sessions;
    }

    public void setSessions(List<ChatSessionDto> sessions) {
        this.sessions = sessions;
    }
}
