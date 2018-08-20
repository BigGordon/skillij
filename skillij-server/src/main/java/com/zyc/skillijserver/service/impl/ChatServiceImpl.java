package com.zyc.skillijserver.service.impl;

import com.zyc.skillijcommon.domain.mongodb.UserChat;
import com.zyc.skillijcommon.domain.mysql.SkillijUser;
import com.zyc.skillijcommon.dto.UserMessage;
import com.zyc.skillijserver.dto.ChatSessionDto;
import com.zyc.skillijserver.dto.RedirectMessageDto;
import com.zyc.skillijserver.dto.UserChatsDto;
import com.zyc.skillijserver.dto.UserPortraitDto;
import com.zyc.skillijserver.repository.mongodb.ChatRepository;
import com.zyc.skillijserver.repository.mysql.AccountRepository;
import com.zyc.skillijserver.service.AccountService;
import com.zyc.skillijserver.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zyc.skillijcommon.constant.Constant.DEFAULT_PORTRAIT_URL;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private ChatRepository chatRepository;

    @Resource
    private AccountService accountService;

    /**
     * 获取用户聊天记录
     * @param username
     * @return
     */
    @Override
    public UserChatsDto getChats(String username) {
        UserChatsDto result = new UserChatsDto();
        List<ChatSessionDto> chatSessionDtoList = new ArrayList<>();
        UserPortraitDto sender = new UserPortraitDto();
        //发送者姓名
        sender.setName(username);
        List<UserChat> userChatList = chatRepository.findUserChatsBySenderName(username);

        if (userChatList.size() == 0) {//第一次开始聊天则添加与“私信助手”的聊天
            userChatList.add(getFistDefaultChat(accountService.findByUsername(username)));
        }

        //遍历对话转换格式
        for (UserChat userChat: userChatList) {
            ChatSessionDto chatSessionDto = new ChatSessionDto();
            UserPortraitDto receiver = new UserPortraitDto();
            //发送者头像路径
            if (sender.getImg() == null) {
                sender.setImg(userChat.getSenderImgUrl());
            }
            //聊天对象
            receiver.setName(userChat.getReceiverName());
            receiver.setImg(userChat.getReceiverImgUrl());
            chatSessionDto.setUser(receiver);
            //对话的Id
            chatSessionDto.setId(userChat.getId());
            //聊天信息
            chatSessionDto.setMessages(userChat.getMessages());
            chatSessionDtoList.add(chatSessionDto);
        }
        //设置发送者
        result.setUser(sender);
        //设置对话
        result.setSessions(chatSessionDtoList);

        return result;
    }

    /**
     * 发送新消息时存到双方数据库
     * @param redirectMessageDto
     */
    @Transactional
    @Override
    public void saveBothRecord(RedirectMessageDto redirectMessageDto) {
        String sender = redirectMessageDto.getSender();
        String receiver = redirectMessageDto.getReceiver();

        //获取用户聊天记录
        UserChat senderRecord = chatRepository.findUserChatsBySenderNameAndReceiverName(sender, receiver).get(0);
        UserChat receiverRecord = chatRepository.findUserChatsBySenderNameAndReceiverName(receiver, sender).get(0);

        //添加发送方和接收方聊天消息
        UserMessage newSenderMessage = redirectMessageDto.convertToUserMessage(true);//发送方
        List<UserMessage> senderMessages = senderRecord.getMessages();
        senderMessages.add(newSenderMessage);

        UserMessage newReceiverMessage = redirectMessageDto.convertToUserMessage(false);//接收方
        List<UserMessage> receiverMessages = receiverRecord.getMessages();
        receiverMessages.add(newReceiverMessage);

        //修改聊天记录
        chatRepository.updateUserChatBySenderNameAndReceiverName(sender, receiver, senderMessages);
        chatRepository.updateUserChatBySenderNameAndReceiverName(receiver, sender, receiverMessages);
    }

    /**
     * 新建聊天
     * @param fromUserName
     * @param toUserName
     * @return
     */
    @Transactional
    @Override
    public String addChat(String fromUserName, String toUserName) {
        SkillijUser toUser = accountService.findByUsername(toUserName);
        if (toUser == null) {
            return "用户不存在";
        }
        SkillijUser fromUser = accountService.findByUsername(fromUserName);

        chatRepository.save(getUserChat(fromUser, toUser));
        chatRepository.save(getUserChat(toUser, fromUser));
        return "新建对话成功";
    }

    /**
     * 得到一个默认的对话
     * @param fromUser
     * @param toUser
     * @return
     */
    private UserChat getUserChat(SkillijUser fromUser, SkillijUser toUser) {
        UserChat userChat = new UserChat();
        userChat.setSenderName(fromUser.getUsername());
        userChat.setSenderId(fromUser.getId());
        userChat.setSenderImgUrl(fromUser.getPortraitUrl() != null? fromUser.getPortraitUrl(): DEFAULT_PORTRAIT_URL);
        userChat.setReceiverName(toUser.getUsername());
        userChat.setReceiverId(toUser.getId());
        userChat.setReceiverImgUrl(toUser.getPortraitUrl() != null? toUser.getPortraitUrl(): DEFAULT_PORTRAIT_URL);
        List<UserMessage> messages = new ArrayList<>();
        userChat.setMessages(messages);

        return userChat;
    }

    /**
     * 添加第一个默认聊天
     * @param user
     * @return
     */
    private UserChat getFistDefaultChat(SkillijUser user) {
        SkillijUser assitant = accountService.findByUsername("私信助手");
        UserChat chat = getUserChat(user, assitant);
        UserMessage guideMessage = new UserMessage();
        guideMessage.setSelf(false);
        guideMessage.setDate(new Date());
        guideMessage.setContent("您好，欢迎使用私信功能。左侧根据用户名可以添加新的对话。");
        chat.getMessages().add(guideMessage);
        return chat;
    }
}
