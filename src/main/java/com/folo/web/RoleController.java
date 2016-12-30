package com.folo.web;

import com.folo.entity.Role;
import com.folo.service.RoleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */

@RestController
@RequestMapping(value="/roles")

public class RoleController {

    @Inject
    RoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Role> getRoleList() {
        return roleService.getRoleList();
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role getRoleById(@PathVariable int roleId) {
        Optional<Role> roleoptional = roleService.getRoleById(roleId);
        return roleoptional.get();
    }
}

