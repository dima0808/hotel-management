package com.bookhotel.hotelmanagement.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {

    private Integer number;

    private Integer size;

    private Boolean isOccupied;

    private LocalDate settlementDate;

    private LocalDate evictionDate;
}
