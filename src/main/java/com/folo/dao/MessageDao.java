package com.folo.dao;

import com.folo.entity.Message;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 *
 * Message is a kind of resource that can be shared to other users.
 * user can share a message to another user or a group of users.
 */
@Repository
public class MessageDao {

    @Inject
    private JdbcTemplate jdbcTemplate; // inject the JdbcTemplate object

    // internal mapper class that converts a db result into an message object.
    private class MessageRowMapper implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message();
            message.setId(rs.getInt("id"));
            message.setSenderId(rs.getInt("sender_id"));
            message.setMessage(rs.getString("message"));
            java.sql.Timestamp timestamp = rs.getTimestamp("ctime");
            message.setCtime(timestamp.toLocalDateTime());
            return message;
        }
    }

    /**
     * get a message object by a message id.
     *
     * @param messageId, unique id for a message.
     * @return an Optional message object.
     */
    public Optional<Message> getMessageById(int messageId) {
        final String sql = "SELECT * FROM messages WHERE id=?";
        Message message = null;
        try {
            message = jdbcTemplate.queryForObject(sql, new Object[]{messageId}, new MessageRowMapper());
        } catch (EmptyResultDataAccessException exp) {
            // ignore
        }

        return Optional.ofNullable(message);
    }

    /**
     * Get a list of message objects, ordered by message.id.
     *
     * @return a list of message objects. The list could be empty
     */
    public List<Message> getMessageList() {
        final String sql = "SELECT * FROM messages ORDER BY id DESC";
        return jdbcTemplate.query(sql, new MessageRowMapper());
    }

    /**
     * get a list of messages that the given user added.
     *
     * @param senderId: the id of the given user
     * @return: a list of Role objects. The list can be empty, but will never be null
     */
    public List<Message> getMessageListFromUserId(int senderId) {
        final String sql = "SELECT * FROM messages " +
                "WHERE owner_id = ? " +
                "ORDER BY id DEC";

        return jdbcTemplate.query(sql, new Object[]{senderId}, new MessageRowMapper());
    }

    /**
     * Get a list of messages that a user received
     *
     * @param userId: the user that the message was sent to
     * @return Optional Photo. can be checked for ifNull()
     */
    public List<Message> getMessageListToUserId(int userId) {

        final String sql = "SELECT * FROM messages " +
                "LEFT JOIN message_share ON (photo_share.photo_id = photos.id) " +
                "WHERE photo_share.user_id = ? " +
                "ORDER BY photos.id DEC";

        return jdbcTemplate.query(sql, new Object[]{userId}, new MessageRowMapper());
    }

    /**
     * Get a list of messages that was sent to a group
     *
     * @param groupId: the targeted group
     * @return a list of messages. can be empty, but never be null
     */
    public List<Message> getMessageListToGroupId(int groupId) {

        final String sql = "SELECT * FROM messages " +
                "LEFT JOIN message_share ON (message_share.message_id = message.id) " +
                "WHERE message_share.group_id = ? " +
                "ORDER BY message.id DEC";

        return jdbcTemplate.query(sql, new Object[]{groupId}, new MessageRowMapper());
    }
}

