package com.zyc.skillijcommon.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2018/7/10.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class UserMessage implements Serializable {
    /**
     * 消息发送时间
     */
    private Date date;

    /**
     * 消息的内容
     */
    private String content;

    /**
     * 是否是自己发出的消息
     */
    private Boolean self;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")//fastjson解析日期
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }
}
