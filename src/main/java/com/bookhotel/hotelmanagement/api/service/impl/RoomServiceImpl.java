package com.bookhotel.hotelmanagement.api.service.impl;

import com.bookhotel.hotelmanagement.api.dto.RoomDto;
import com.bookhotel.hotelmanagement.api.entity.Hotel;
import com.bookhotel.hotelmanagement.api.entity.Room;
import com.bookhotel.hotelmanagement.api.repository.RoomRepository;
import com.bookhotel.hotelmanagement.api.service.HotelService;
import com.bookhotel.hotelmanagement.api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    @Override
    public List<Room> findAll(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }

    @Override
    public Room findByNumber(Long hotelId, Integer number) {

        Hotel hotel = hotelService.findById(hotelId);

        for (Room room : hotel.getRooms()) {
            if (room.getNumber().equals(number)) return room;
        }
        return null;
    }

    @Override
    public Room create(Long hotelId, RoomDto roomDto) {

        Room room = Room.builder()
                .number(roomDto.getNumber())
                .size(roomDto.getSize())
                .pricePerDay(roomDto.getPricePerDay())
                .hotel(hotelService.findById(hotelId))
                .build();

        return roomRepository.save(room);
    }

    @Override
    public Room update(Long hotelId, Integer number, RoomDto roomDto) {

        Room existingRoom = findByNumber(hotelId, number);

        if (roomDto.getSize() != null)existingRoom.setSize(roomDto.getSize());
        if (roomDto.getPricePerDay() != null)existingRoom.setPricePerDay(roomDto.getPricePerDay());

        return roomRepository.save(existingRoom);
    }

    @Override
    public void deleteByNumber(Long hotelId, Integer number) {
        Hotel hotel = hotelService.findById(hotelId);

        List<Room> rooms = hotel.getRooms();
        Iterator<Room> iterator = rooms.iterator();

        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.getNumber().equals(number)) {
                iterator.remove();
                hotel.setRooms(rooms);
                hotelService.update(hotel);
                roomRepository.delete(room);
                break;
            }
        }
    }
}
