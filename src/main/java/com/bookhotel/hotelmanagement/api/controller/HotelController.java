package com.bookhotel.hotelmanagement.api.controller;

import com.bookhotel.hotelmanagement.api.dto.HotelDto;
import com.bookhotel.hotelmanagement.api.entity.Hotel;
import com.bookhotel.hotelmanagement.api.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getHotel(@PathVariable Long id,
                                                        @RequestParam(required = false) String include) {
        return ResponseEntity.ok(hotelService.convertHotelToMap(hotelService.findById(id), include));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllHotels(@RequestParam(required = false) String include) {

        List<Map<String, Object>> hotels = new ArrayList<>();

        for (Hotel hotel : hotelService.findAll()) {
            hotels.add(hotelService.convertHotelToMap(hotel, include));
        }

        return ResponseEntity.ok(hotels);
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.createHotel(hotelDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
