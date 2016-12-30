package com.folo.dao;

import com.folo.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 * Data access layer that interact with database using SQL
 */
@Repository
public class UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    // internal mapper class that converts a db result into a User object.
    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    /**
     * get an user object by a user id.
     * @param userId, unique id for a user.
     * @return an User object.
     */
    public Optional<User> getUserById(int userId) {
        User user = null;
        final String sql = "SELECT * FROM users WHERE id=?";
        try{
            user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserRowMapper());
        }catch (EmptyResultDataAccessException exp) {
            // ignore
        }
        return Optional.ofNullable(user);
    }

    /**
     * Get a list of user objects, ordered by user id.
     * @return a list of user objects.
     */
    public List<User> getUserList() {
        final String sql = "SELECT * FROM users ORDER BY id ASC";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    /**
     * save and user object into database users table
     * @param user: input user object, the id filed will be ignored
     * @return: updated user object with the newly created user Id.
     */
    public User create(final User user) {
        if(user==null) return user;

        final String sql = "INSERT INTO users(first_name, last_name, email, password) values(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                return ps;
            }
        }, holder);

        int newUserId = holder.getKey().intValue();
        user.setId(newUserId);
        return user;
    }

    public void update(final User user) {
        if(user==null)
            return;

        final String sql = "UPDATE users SET first_name=?, last_name=?, email=?, password=? " +
                "";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getPassword());
                return ps;
            }
        }, holder);
    }

    /**
     * get a list of users that has the given role Id
     * @param roleId: the id of the given role
     * @return: a list of User objects.
     */
    public List<User> getUserListByRoleId(int roleId) {
        final String sql = "SELECT users.* from users " +
                "LEFT JOIN user_role on (user_role.user_id = users.id) " +
                "WHERE role.id = ? " +
                "ORDER BY users.id ASC";
        return jdbcTemplate.query(sql,  new Object[]{roleId}, new UserRowMapper());
    }
}
