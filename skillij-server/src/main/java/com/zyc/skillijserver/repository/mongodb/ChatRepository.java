package com.zyc.skillijserver.repository.mongodb;

import com.zyc.skillijcommon.domain.mongodb.UserChat;
import com.zyc.skillijcommon.dto.UserMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created on 2018/7/10.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public interface ChatRepository extends MongoRepository<UserChat, String> {

    /**
     * 根据发送者Id找到所有对话
     * @param senderName
     * @return
     */
    @Query("select u from UserChat u where u.senderName = ?1")
    List<UserChat> findUserChatsBySenderName(String senderName);

    /**
     * 根据发信人和收信人姓名查找聊天
     * @param senderName
     * @param receiverName
     * @return
     */
    @Query("select u from UserChat u where u.senderName = ?1 and u.receiverName = ?2")
    List<UserChat> findUserChatsBySenderNameAndReceiverName(String senderName, String receiverName);

    /**
     * 根据发信人和收信人姓名修改聊天消息
     * @param senderName
     * @param receiverName
     * @param messages
     */
    @Modifying(clearAutomatically = true)
    void updateUserChatBySenderNameAndReceiverName(String senderName, String receiverName, List<UserMessage> messages);
}
