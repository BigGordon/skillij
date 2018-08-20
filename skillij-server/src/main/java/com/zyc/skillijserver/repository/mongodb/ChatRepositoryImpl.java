package com.zyc.skillijserver.repository.mongodb;

import com.zyc.skillijcommon.domain.mongodb.UserChat;
import com.zyc.skillijcommon.dto.UserMessage;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.List;

/** mongodb中update需要实现
 * Created on 2018/7/17.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class ChatRepositoryImpl {

    @Resource
    private MongoTemplate mongoTemplate;

    public void updateUserChatBySenderNameAndReceiverName(String senderName, String receiverName, List<UserMessage> messages) {
        Query query = new Query();
        query.addCriteria(Criteria.where("senderName").is(senderName));
        query.addCriteria(Criteria.where("receiverName").is(receiverName));
        Update update = Update.update("messages", messages);
        mongoTemplate.updateFirst(query, update, UserChat.class);
    }
}
