package com.bookhotel.hotelmanagement.api.service.impl;

import com.bookhotel.hotelmanagement.api.entity.Hotel;
import com.bookhotel.hotelmanagement.api.entity.Image;
import com.bookhotel.hotelmanagement.api.repository.StorageRepository;
import com.bookhotel.hotelmanagement.api.service.HotelService;
import com.bookhotel.hotelmanagement.api.service.StorageService;
import com.bookhotel.hotelmanagement.api.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;
    private final HotelService hotelService;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, HotelService hotelService) {
        this.storageRepository = storageRepository;
        this.hotelService = hotelService;
    }

    @Override
    public Image findImage(Long hotelId) {
        return hotelService.findById(hotelId).getImage();
    }

    @Override
    public Image uploadImage(Long hotelId, MultipartFile file) throws IOException {

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .hotel(hotelService.findById(hotelId)).build();

        return storageRepository.save(image);
    }

    @Override
    public Image updateImage(Long hotelId, MultipartFile file) throws IOException {

        Hotel hotel = hotelService.findById(hotelId);

        Image existingImage = hotel.getImage();

        existingImage.setName(file.getName());
        existingImage.setType(file.getContentType());
        existingImage.setImageData(ImageUtils.compressImage(file.getBytes()));

        return storageRepository.save(existingImage);
    }

    @Override
    public void deleteImage(Long hotelId) {

        Hotel hotel = hotelService.findById(hotelId);
        Image image = hotel.getImage();

        hotel.setImage(null);
        hotelService.update(hotel);
        storageRepository.delete(image);
    }

    @Override
    public byte[] downloadImage(Image image) {
        return ImageUtils.decompressImage(image.getImageData());
    }
}
