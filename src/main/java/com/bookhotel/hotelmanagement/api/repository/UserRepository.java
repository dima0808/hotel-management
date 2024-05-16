package com.bookhotel.hotelmanagement.api.repository;

import com.bookhotel.hotelmanagement.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameOrEmail(String username, String email);
}
