package com.folo.web;

import com.folo.entity.Photo;
import com.folo.service.PhotoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiang on 12/25/2016.
 */

@RestController
@RequestMapping(value="/photos")
public class PhotoController {

    @Inject
    PhotoService photoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Photo> getPhotoList() {
        return photoService.getPhotoList();
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        //storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }


    @RequestMapping(value = "/{photoId}", method = RequestMethod.GET)
    public Photo getPhotoById(@PathVariable int photoId) {
        Optional<Photo> photoOptional = photoService.getPhotoById(photoId);
        return photoOptional.get();
    }
}

