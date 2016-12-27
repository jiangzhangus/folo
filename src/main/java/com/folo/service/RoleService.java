package com.folo.service;

import com.folo.dao.RoleDao;
import com.folo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Created by jiang on 12/25/2016.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Optional<Role> getRoleById(int roleId) {
        return roleDao.getRoleById(roleId);
    }

    /**
     * Get the last name of the user.
     * @param roleId: the unique role id
     * @return String: the name of the role
     */
    public String getNameById(int roleId) {
        Optional<Role> roleOptional = getRoleById(roleId);
        if(roleOptional.isPresent())
            return roleOptional.get().getName();
        else {
            return "Anonymous role";
        }
    }

    /**
     * get all the roles from the roles table
     * @return: a list of role objects.
     */
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }

    /**
     * get a list of roles that a user has.
     * @param userId
     * @return
     */
    public List<Role> getRoleListByUserId(int userId) {
        return roleDao.getRoleListByUserId(userId);
    }

    /**
     * Get one top priority role for a user.
     * @param userId
     * @return
     */
    public Optional<Role> getTopRoleByUserId(int userId) {
        return roleDao.getTopRoleByUserId(userId);
    }
}
