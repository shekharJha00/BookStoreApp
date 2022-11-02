package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    String createUser(UserDTO userDTO);

    List<User> getAllUserData();

    User getUserDataByToken(String token);

    User updateUserRegistration(String token, UserDTO userDTO);

    void deleteUser(String token);

    User userLogin(String email, String password);

    User forgotPassword(Long userId, String email);
}
