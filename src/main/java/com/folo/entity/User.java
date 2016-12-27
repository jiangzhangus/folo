package com.folo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiang on 12/25/2016.
 */

// maps to user table
@Entity
public class User {
    @Id
    private int id;

    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public User() {}

    public User(int id, String first_name, String last_name, String email, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, first_name='%s', last_name='%s', email='%s', password='%s']",
                id, first_name, last_name, email, password);
    }

    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();
        if (first_name != null) {
            sb.append(first_name).append(" ");
        }
        if (last_name != null) {
            sb.append(last_name);
        }
        if (first_name == null && last_name == null) {
            sb.append("Anonymous");
        }
        return sb.toString();
    }

    // getters and setters
    public int getId() { return id;}
    public void setId(int id) {this.id = id;}

    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) {this.first_name = first_name;}

    public String getLastName() { return last_name; }
    public void setLastName(String last_name) {this.last_name = last_name;}

    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = first_name;}

    public String getPassword() { return password;}
    public void setPassword(String password) {this.password = password;}
}
