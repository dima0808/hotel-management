package com.bookhotel.hotelmanagement.api.service;

import com.bookhotel.hotelmanagement.api.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    Image findImage(Long hotelId);

    Image uploadImage(Long hotelId, MultipartFile file) throws IOException;

    Image updateImage(Long hotelId, MultipartFile file) throws IOException;

    void deleteImage(Long hotelId);

    byte[] downloadImage(Image image);
}
