package com.bookhotel.hotelmanagement.api.service;


import com.bookhotel.hotelmanagement.api.dto.SignInDto;
import com.bookhotel.hotelmanagement.api.dto.SignUpDto;
import com.bookhotel.hotelmanagement.api.entity.User;

public interface AuthService {

    User signIn(SignInDto signInDto);

    User signUp(SignUpDto signUpDto);

    void logout();
}
