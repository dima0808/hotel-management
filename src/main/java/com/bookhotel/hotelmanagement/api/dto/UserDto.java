package com.bookhotel.hotelmanagement.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String phoneNumber;
}