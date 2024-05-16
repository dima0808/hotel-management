package com.bookhotel.hotelmanagement.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private Boolean isHotelAdmin;

    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_rooms",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_users_rooms_user_id",
                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE")),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_users_rooms_room_id",
                            foreignKeyDefinition = "FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE")))
    private List<Room> rooms;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_rooms",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_users_hotels_user_id",
                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE")),
            inverseJoinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_users_hotels_hotel_id",
                            foreignKeyDefinition = "FOREIGN KEY (hotel_id) REFERENCES hotels(id) ON DELETE CASCADE")))
    private List<Hotel> hotels;
}
