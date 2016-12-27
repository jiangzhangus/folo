package com.folo.web;

/**
 * Created by jiang on 12/23/2016.
 */


import com.folo.entity.User;
import com.folo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * controller serves as the gateway for REST API calls
 */
@RestController
@RequestMapping(value="/users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody User userObj) {
        User newUser = userService.add(userObj);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/{userId}", method= RequestMethod.GET)
    public User getUserById(@PathVariable int userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        return userOptional.get();
    }

    @RequestMapping(value="/{userId}", method = RequestMethod.POST)
    ResponseEntity<?> update(@RequestBody User userObj) {
        userService.update(userObj);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userId}")
                .buildAndExpand(userObj.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value="/role/{roleId}", method= RequestMethod.GET)
    public List<User> getUserListByRoleId(@PathVariable int userId) {
        return userService.getUserListByRoleId(userId);
    }
}
