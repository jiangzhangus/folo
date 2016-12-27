package com.folo.dao;

import com.folo.entity.Photo;
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
 * Photo is a kind of resource that can be shared to other users.
 * user can share a photo to another user or a group of users.
 */
@Component
public class PhotoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate; // inject the JdbcTemplate object

    // internal mapper class that converts a db result into an photo object.
    private class PhotoRowMapper implements RowMapper<Photo> {

        @Override
        public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Photo photo = new Photo();
            photo.setId(rs.getInt("id"));
            photo.setOwnerId(rs.getInt("owner_id"));
            photo.setComments(rs.getString("comments"));
            photo.setUuid(rs.getString("uuid"));
            return photo;
        }
    }

    /**
     * get a photo object by a photo id.
     * @param photoId, unique id for a photo.
     * @return an Optional photo object.
     */
    public Optional<Photo> getPhotoById(int photoId) {
        final String sql = "SELECT * FROM photos WHERE id=?";
        Photo photo = null;
        try{
            photo = jdbcTemplate.queryForObject(sql, new Object[]{photoId}, new PhotoRowMapper());
        }catch (EmptyResultDataAccessException exp) {
            // ignore
        }

        return Optional.ofNullable(photo);
    }

    /**
     * Get a list of role objects, ordered by role.id.
     * @return a list of role objects. The list could be empty
     */
    public List<Photo> getPhotoList() {
        final String sql = "SELECT * FROM photos ORDER BY id DESC";
        return jdbcTemplate.query(sql, new PhotoRowMapper());
    }

    /**
     * get a list of photos that the given user added.
     * @param ownerId: the id of the given user
     * @return: a list of Role objects. The list can be empty, but will never be null
     */
    public List<Photo> getPhotoListByUserId(int ownerId) {
        final String sql = "SELECT * FROM photos " +
                "WHERE owner_id = ? " +
                "ORDER BY id DEC";

        return jdbcTemplate.query(sql, new Object[]{ownerId}, new PhotoRowMapper());
    }

    /**
     * Get a list of photos that a user received
     * @param userId: the user that the photos was sent to
     * @return Optional Photo. can be checked for ifNull()
     */
    public List<Photo> getPhotoListToUserId(int userId) {

        final String sql = "SELECT * FROM photos " +
                "LEFT JOIN photo_share ON (photo_share.photo_id = photos.id) " +
                "WHERE photo_share.user_id = ? " +
                "ORDER BY photos.id DEC";

        return jdbcTemplate.query(sql, new Object[]{userId}, new PhotoRowMapper());
    }

    /**
     * Get a list of photos that sent to a group
     * @param groupId: the targeted group
     * @return a list of photos. can be empty, but never be null
     */
    public List<Photo> getPhotoListToGroupId(int groupId) {

        final String sql = "SELECT * FROM photos " +
                "LEFT JOIN photo_share ON (photo_share.photo_id = photos.id) " +
                "WHERE photo_share.group_id = ? " +
                "ORDER BY photos.id DEC";

        return jdbcTemplate.query(sql, new Object[]{groupId}, new PhotoRowMapper());
    }
}
