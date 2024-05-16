package com.bookhotel.hotelmanagement.api.service.impl;

import com.bookhotel.hotelmanagement.api.dto.HotelDto;
import com.bookhotel.hotelmanagement.api.dto.RoomDto;
import com.bookhotel.hotelmanagement.api.entity.Hotel;
import com.bookhotel.hotelmanagement.api.entity.Room;
import com.bookhotel.hotelmanagement.api.repository.HotelRepository;
import com.bookhotel.hotelmanagement.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElse(null); // HotelNotFoundException
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel createHotel(HotelDto hotelDto) {

        Hotel hotel = Hotel.builder()
                .name(hotelDto.getName())
                .description(hotelDto.getDescription())
                .address(hotelDto.getAddress())
                .build();

        List<Room> rooms = new ArrayList<>();
        for (RoomDto roomDto : hotelDto.getRooms()) {
            Room room = Room.builder()
                    .number(roomDto.getNumber())
                    .size(roomDto.getSize())
                    .isOccupied(roomDto.getIsOccupied())
                    .settlementDate(roomDto.getSettlementDate())
                    .evictionDate(roomDto.getEvictionDate())
                    .hotel(hotel).build();
            rooms.add(room);
        }

        hotel.setRooms(rooms);

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> convertHotelToMap(Hotel hotel, String include) {

        Map<String, Object> hotelMap = new HashMap<>();

        hotelMap.put("address", hotel.getAddress());
        hotelMap.put("description", hotel.getDescription());
        hotelMap.put("name", hotel.getName());
        hotelMap.put("id", hotel.getId());

        if (include == null) return hotelMap;

        List<String> includeParameters = new ArrayList<>(Arrays.asList(include.split(",")));
        if (includeParameters.contains("rooms")) hotelMap.put("rooms", hotel.getRooms());
        return hotelMap;
    }
}
