package com.zyc.skillijserver.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zyc.skillijcommon.dto.UserMessage;

import java.util.Date;

/**
 * 一条即时聊天信息
 * Created on 2018/7/16.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class RedirectMessageDto {

    /**
     * 聊天内容
     */
    private String content;

    /**
     * 收信人
     */
    private String receiver;

    /**
     * 发信人
     */
    private String sender;

    /**
     * 发信时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")//fastjson解析日期
    private Date date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 交换发送者和接收者
     */
    public void switchSenderAndReceiver() {
        String tmp = this.sender;
        this.sender = this.receiver;
        this.receiver = tmp;
    }

    /**
     * 根据是否是发信人自己转化为一条聊天消息
     * @param self
     * @return
     */
    public UserMessage convertToUserMessage(Boolean self) {
        UserMessage userMessage = new UserMessage();
        userMessage.setContent(this.content);
        userMessage.setDate(this.date);
        userMessage.setSelf(self);

        return userMessage;
    }
}
