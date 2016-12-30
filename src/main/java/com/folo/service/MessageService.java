package com.folo.service;

import com.folo.dao.MessageDao;
import com.folo.entity.Message;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */
@Service
public class MessageService {

    @Inject
    private MessageDao messageDao;

    public Optional<Message> getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    public List<Message> getMessageListFromUser(int userId) {
        return messageDao.getMessageListFromUserId(userId);
    }

    public List<Message> getMessageListToUser(int userId) {
        return messageDao.getMessageListToUserId(userId);
    }

    public List<Message> getMessageListToGroup(int groupId) {
        return messageDao.getMessageListToGroupId(groupId);
    }
}
