package com.bookhotel.hotelmanagement.api.repository;

import com.bookhotel.hotelmanagement.api.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
