package com.folo.web;

import com.folo.entity.Message;
import com.folo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */
@RestController
@RequestMapping(value="/messages")

public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/user/{receiverId}", method= RequestMethod.GET)
    public List<Message> getMessagesToUser(@PathVariable int receiverId) {
        return messageService.getMessageListToUser(receiverId);
    }
    @RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
    public Message getMessageById(int messageId) {
        Optional<Message> messageOptional = messageService.getMessageById(messageId);
        return messageOptional.get();
    }
}
