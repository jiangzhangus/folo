package com.folo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

/**
 * Created by jiang on 12/25/2016.
 */

// maps to groups table
@Entity
public class Group {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int owner_id;

    public Group() {}
    public Group(int id, String name, int owner_id) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
    }


    @Override
    public String toString() {
        return String.format("User[id=$d, name='%s', owner_id=%d]",
                id, name, owner_id);
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return owner_id;
    }

    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }
}
