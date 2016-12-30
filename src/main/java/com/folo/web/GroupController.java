package com.folo.web;

import com.folo.entity.Group;
import com.folo.service.GroupService;
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
@RequestMapping(value="/groups")

public class GroupController {

    @Inject
    GroupService groupService;

    @RequestMapping(value = "/{groupId}", method= RequestMethod.GET)
    public Group getGroupById(@PathVariable int groupId) {
        Optional<Group> groupOptional = groupService.getGroupById(groupId);
        return groupOptional.get();
    }

    @RequestMapping(value = "/user/{userId}", method= RequestMethod.GET)
    public List<Group> getGroupListByUserId(@PathVariable int userId) {
        return groupService.getGroupListByUserId(userId);
    }
}
