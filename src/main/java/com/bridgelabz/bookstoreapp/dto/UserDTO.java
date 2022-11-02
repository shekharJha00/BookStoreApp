package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public @ToString class UserDTO {
    @NotEmpty(message = "First name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Invalid first name")
    public String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$", message = "Invalid last name")
    public String lastName;
    @NotEmpty(message = "Email cannot be empty!")
    @Pattern(regexp = "^[a-zA-Z-9]+([._+-]*[0-9A-Za-z]+)*@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-z]{2,4})?$", message = "Invalid email")
    public String email;
    @NotEmpty(message = "Password cannot be empty!")
    public String password;
    @NotEmpty(message = "Address cannot be empty")
    public String address;
}