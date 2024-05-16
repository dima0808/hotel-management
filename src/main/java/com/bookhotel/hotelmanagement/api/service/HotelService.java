package com.bookhotel.hotelmanagement.api.service;

import com.bookhotel.hotelmanagement.api.dto.HotelDto;
import com.bookhotel.hotelmanagement.api.entity.Hotel;

import java.util.List;
import java.util.Map;

public interface HotelService {

    Hotel findById(Long id);

    List<Hotel> findAll();

    Hotel createHotel(HotelDto hotelDto);

    void deleteById(Long id);

    Map<String, Object> convertHotelToMap(Hotel hotel, String include);
}
