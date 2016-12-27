package com.folo.dao;

import com.folo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 *
 * Role is the sub group of users.
 * A user can have multiple roles and multiple user can have the same role.
 * i.e.: many-to-many
 *
 * roles table is pre-defined, cannot be changed through API.
 */
@Component
public class RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate; // inject the JdbcTemplate object

    // internal mapper class that converts a db result into an Role object.
    private class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            return role;
        }
    }

    /**
     * get an role object by a role id.
     * @param roleId, unique id for a role.
     * @return an Optional role object.
     */
    public Optional<Role> getRoleById(int roleId) {
        final String sql = "SELECT * FROM roles WHERE id=?";
        Role role = null;
        try{
            role = jdbcTemplate.queryForObject(sql, new Object[]{roleId}, new RoleRowMapper());
        }catch (EmptyResultDataAccessException exp) {
            // ignore
        }

        return Optional.ofNullable(role);
    }

    /**
     * Get a list of role objects, ordered by role.id.
     * @return a list of role objects. The list could be empty
     */
    public List<Role> getRoleList() {
        final String sql = "SELECT * FROM roles ORDER BY id ASC";
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    /**
     * get a list of roles that the given user has.
     * @param userId: the id of the given user
     * @return: a list of Role objects. The list can be empty, but will never be null
     */
     public List<Role> getRoleListByUserId(int userId) {
         final String sql = "SELECT roles.id, roles.name " +
                "FROM roles LEFT JOIN user_role ON roles.id = user_role.role_id " +
                "WHERE user_role.user_id = ? " +
                "ORDER BY roles.id DEC";

         return jdbcTemplate.query(sql, new Object[]{userId}, new RoleRowMapper());
     }

    /**
     * Get the user's most high priority role (most powerful role) by an user id
     * @param userId: the user we care about
     * @return Optional Role. can be checked for ifNull()
     */
    public Optional<Role> getTopRoleByUserId(int userId) {
         // roles will never be null, ref above getRoleListByUserId
         List<Role> roles = getRoleListByUserId(userId);
         if(roles.size() > 0) {
             // the list if orderred by role.id. small value if id means higher priority.
            return Optional.of(roles.get(0));
         }
         return Optional.ofNullable(null);
     }
}
