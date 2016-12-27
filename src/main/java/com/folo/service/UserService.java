package com.folo.service;

import com.folo.dao.RoleDao;
import com.folo.dao.UserDao;
import com.folo.entity.Role;
import com.folo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public Optional<User> getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    /**
     * Get the first name of the user.
     * @param userId: the unique user id
     * @return String: friendly user name
     */
    public String getFirstNameById(int userId) {
        Optional<User> userOptional = userDao.getUserById(userId);
        if(userOptional.isPresent())
            return userOptional.get().getFirstName();
        else {
            return "Anonymous";
        }
    }

    /**
     * Get the last name of the user.
     * @param userId: the unique user id
     * @return String: friendly user name
     */
    public String getLastNameById(int userId) {
        Optional<User> userOptional = userDao.getUserById(userId);
        if(userOptional.isPresent())
            return userOptional.get().getLastName();
        else {
            return "Anonymous";
        }
    }

    /**
     * Get a name string that combines first_name and last_name fields.
     * @param userId: the unique user id
     * @return String: friendly user name
     */
    public String getDisplayNameById(int userId) {
        Optional<User> userOpt = userDao.getUserById(userId);
        if(userOpt.isPresent())
            return userOpt.get().getDisplayName();
        else {
            return "Anonymous";
        }
    }

    /**
     * Get a name string that combines first_name and last_name fields.
     * @param userId: the unique user id
     * @return String: friendly user name
     */
    public String getPasswordById(int userId) {
        Optional<User> userOpt = userDao.getUserById(userId);
        if(userOpt.isPresent())
            return userOpt.get().getPassword();
        else {
            return "";
        }
    }


    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public List<User> getUserListByRoleId(int roleId) {
        return userDao.getUserListByRoleId(roleId);
    }

    public List<Role> getRoleListByUserId(int userId) {
        return roleDao.getRoleListByUserId(userId);
    }

    public User add(User input) {
        return userDao.create(input);
    }

    public void update(User input) {
        userDao.update(input);
    }
}

