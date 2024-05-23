package com.bookhotel.hotelmanagement.api.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelDto {

    private String name;

    private String description;

    private String address;

    private String owner;

    private List<RoomDto> rooms;
}
