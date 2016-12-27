package com.folo.web;

import com.folo.entity.Photo;
import com.folo.service.PhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */

@RestController
@RequestMapping(value="/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Photo> getPhotoList() {
        return photoService.getPhotoList();
    }

    @RequestMapping(value = "/{photoId}", method = RequestMethod.GET)
    public Photo getPhotoById(@PathVariable int photoId) {
        Optional<Photo> photoOptional = photoService.getPhotoById(photoId);
        return photoOptional.get();
    }
}

