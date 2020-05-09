package ru.petr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.petr.entity.Image;
import ru.petr.service.ImageService;

@Controller
public class ImageController {
    private static final String IMAGE_URL = "/image";
    @Autowired
    private ImageService service;

    @GetMapping(IMAGE_URL)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@RequestParam("id") int advertisementId) {
        Image primeMinisterOfIndia = service.findByAdvertisementId(advertisementId);
        byte[] imageBytes = primeMinisterOfIndia.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
}
