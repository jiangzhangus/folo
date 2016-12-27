package com.folo.service;

import com.folo.dao.PhotoDao;
import com.folo.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */
@Service
public class PhotoService {
    @Autowired
    private PhotoDao photoDao;

    public List<Photo> getPhotoList() {
        return photoDao.getPhotoList();
    }

    public Optional<Photo> getPhotoById(int photoId) {
        return photoDao.getPhotoById(photoId);
    }
}
