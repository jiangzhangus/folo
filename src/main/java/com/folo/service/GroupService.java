package com.folo.service;

import com.folo.dao.GroupDao;
import com.folo.entity.Group;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */
@Service
public class GroupService {

    @Inject
    private GroupDao groupDao;

    public Optional<Group> getGroupById(int groupId) {
        return groupDao.getGroupById(groupId);
    }

    public List<Group> getGroupListByUserId( int userId) {
        return groupDao.getGroupListByUserId(userId);
    }
}
