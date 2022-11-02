package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.JMS;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private JMS emailSender;



    @Override
    public String createUser(UserDTO userDTO) {
        User user = userRepository.save(new User(userDTO));
        String token = jwtToken.createToken(user.getUserId());
        emailSender.sendEmail(user.getEmail(), "Registration confirmation", "User " + user.getFirstName() + " "
                + user.getLastName() + ". Thank you for Registering." + '\n' + user);
        return token;
    }

    @Override
    public List<User> getAllUserData() {
        if (userRepository.findAll().isEmpty()) {
            System.out.println("No data found");
            throw new BookStoreException("No User found in database.");
        } else return userRepository.findAll();
    }

    @Override
    public User getUserDataByToken(String token) {
        return userRepository.findById(jwtToken.decodeToken(token)).orElseThrow(()
                -> new BookStoreException("User not found"));
    }

    @Override
    public User updateUserRegistration(String token, UserDTO userDTO) {
        User userRegistration = userRepository.findById(jwtToken.decodeToken(token)).orElse(null);
        if (userRegistration != null) {
            userRegistration.setFirstName(userDTO.getFirstName());
            userRegistration.setLastName(userDTO.getLastName());
            userRegistration.setEmail(userDTO.getEmail());
            userRegistration.setPassword(userDTO.getPassword());
            userRegistration.setAddress(userDTO.getAddress());
        }
        assert userRegistration != null;
        return userRepository.save(userRegistration);
    }

    public void deleteUser(String token) {
        userRepository.delete(this.getUserDataByToken(token));
    }

    @Override
    public User userLogin(String email, String password) {
        return userRepository.loginValidation(email, password);
    }

    @Override
    public User forgotPassword(Long userId, String email) {
        User user = userRepository.getUserByEmail(email);
        if (user != null)
            return user;
        else
            throw new BookStoreException("User with email id " + userId + " not found");
    }
}