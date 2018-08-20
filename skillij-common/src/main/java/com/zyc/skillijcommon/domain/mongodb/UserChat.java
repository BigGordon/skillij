package com.zyc.skillijcommon.domain.mongodb;

import com.zyc.skillijcommon.dto.UserMessage;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 2018/7/10.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Document
public class UserChat implements Serializable {

    @Id
    private String id;

    @Column(name = "senderId", columnDefinition = "BIGINT(20) NOT NULL COMMENT '发送者Id'")
    private Long senderId;

    @Column(name = "senderName", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '发送者名称'")
    private String senderName;

    @Column(name = "senderImgUrl", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '发送者头像路径'")
    private String senderImgUrl;

    @Column(name = "receiverId", columnDefinition = "BIGINT(20) NOT NULL COMMENT '接收者Id'")
    private Long receiverId;

    @Column(name = "receiverName", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '接收者名称'")
    private String receiverName;

    @Column(name = "receiverImgUrl", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '接收者头像路径'")
    private String receiverImgUrl;

    @Column(name = "messages", columnDefinition = "VARCHAR(200) NOT NULL COMMENT '聊天消息'")
    private List<UserMessage> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderImgUrl() {
        return senderImgUrl;
    }

    public void setSenderImgUrl(String senderImgUrl) {
        this.senderImgUrl = senderImgUrl;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverImgUrl() {
        return receiverImgUrl;
    }

    public void setReceiverImgUrl(String receiverImgUrl) {
        this.receiverImgUrl = receiverImgUrl;
    }

    public List<UserMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<UserMessage> messages) {
        this.messages = messages;
    }
}
