package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    public User(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.password = userDTO.password;
        this.address = userDTO.address;
    }
}


