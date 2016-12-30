package com.folo.dao;

/**
 * Created by jiang on 12/25/2016.
 *
 * Group is the a set of users.
 * A user can be in multiple groups and multiple user can be in the same group.
 * i.e.: many-to-many
 *
 */

import com.folo.entity.Group;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupDao {

    @Inject
    private JdbcTemplate jdbcTemplate; // inject the JdbcTemplate object

    // internal mapper class that converts a db result into an Group object.
    private class GroupRowMapper implements RowMapper<Group> {
        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
            group.setOwnerId(rs.getInt("owner_id"));
            return group;
        }
    }

    /**
     * get an group object by a group id.
     * @param groupId, unique id for a group.
     * @return an Optional Group object.
     */
    public Optional<Group> getGroupById(int groupId) {
        final String sql = "SELECT * FROM groups WHERE id=?";
        Group group = null;
        try{
            group = jdbcTemplate.queryForObject(sql, new Object[]{groupId}, new GroupRowMapper());
        }catch (EmptyResultDataAccessException exp) {
            // ignore
        }

        return Optional.ofNullable(group);
    }

    /**
     * Get a list of group objects, ordered by group.id.
     * @return a list of group objects. The list can be empty, but never be null
     */
    public List<Group> getGroupList() {
        final String sql = "SELECT * FROM groups ORDER BY id ASC";
        return jdbcTemplate.query(sql, new GroupRowMapper());
    }

    /**
     * get a list of groups that the given user is in.
     * @param userId: the id of the given user
     * @return: a list of Group objects. The list can be empty, but will never be null
     */
    public List<Group> getGroupListByUserId(int userId) {
        final String sql = "SELECT groups.*  " +
                "FROM groups LEFT JOIN user_group ON groups.id = user_group.group_id " +
                "WHERE user_group.user_id = ? " +
                "ORDER BY group.id DEC";

        return jdbcTemplate.query(sql, new Object[]{userId}, new GroupRowMapper());
    }

    /**
     * get a list of groups that the given user created.
     * @param ownerId: the id of the given user
     * @return: a list of Group objects. The list can be empty, but will never be null
     */
    public List<Group> getGroupListByOwnerId(int ownerId) {
        final String sql = "SELECT * " +
                "FROM groups " +
                "WHERE owner_id = ? " +
                "ORDER BY id DEC";

        return jdbcTemplate.query(sql, new Object[]{ownerId}, new GroupRowMapper());
    }

}


