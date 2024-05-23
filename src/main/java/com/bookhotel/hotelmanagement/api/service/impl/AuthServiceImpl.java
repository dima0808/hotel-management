package com.bookhotel.hotelmanagement.api.service.impl;

import com.bookhotel.hotelmanagement.api.dto.SignInDto;
import com.bookhotel.hotelmanagement.api.dto.SignUpDto;
import com.bookhotel.hotelmanagement.api.entity.User;
import com.bookhotel.hotelmanagement.api.repository.UserRepository;
import com.bookhotel.hotelmanagement.api.service.AuthService;
import com.bookhotel.hotelmanagement.api.service.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signIn(SignInDto signInDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getUsernameOrEmail(), signInDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getName());

        return userRepository.findByUsernameOrEmail(signInDto.getUsernameOrEmail(), signInDto.getUsernameOrEmail())
                .orElse(null);
    }

    @Override
    public User signUp(SignUpDto signUpDto) {

        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new UserAlreadyExistException("username", signUpDto.getUsername());
        }


        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new UserAlreadyExistException("email", signUpDto.getEmail());
        }

        User user = User.builder()
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .firstName(signUpDto.getFirstName())
                .secondName(signUpDto.getSecondName()).build();

        user.setRoles(signUpDto.getRoles());

        return userRepository.save(user);
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
