package com.folo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiang on 12/25/2016.
 */
// Maps to photos table
@Entity
public class Photo {
    @Id
    private int id;

    private int owner_id;
    private String comments;
    private String uuid;

    public Photo() {}

    public Photo(int id, int owner_id, String comments, String uuid) {
        this.id = id;
        this.owner_id = owner_id;
        this.comments = comments;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return String.format("Photo[id=%d, owner_id=%d, comments='%s', uuid='%s']",
                id, owner_id, comments, uuid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
