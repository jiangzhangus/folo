package com.folo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDateTime;

/**
 * Created by jiang on 12/25/2016.
 */

// maps to messages table
@Entity
public class Message {
    @Id
    private int id;

    private String message;
    private int sender_id;
    private LocalDateTime ctime; // the time that the message was craeted.

    public Message() {}

    public Message(int id, String message, int sender_id, LocalDateTime ctime) {
        this.id = id;
        this.message = message;
        this.sender_id = sender_id;
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return String.format("Message[id=%d, message='%s', sender_id=%d, ctime='%s']",
                id, message, sender_id, ctime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSenderId() {
        return sender_id;
    }

    public void setSenderId(int sender_id) {
        this.sender_id = sender_id;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }
    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }
}
