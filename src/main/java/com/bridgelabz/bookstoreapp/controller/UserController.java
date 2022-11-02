package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.model.User;
import com.bridgelabz.bookstoreapp.service.IUserService;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/bookstore/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    JWTToken jwtToken;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> createUserRegistrationData(@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Data Added Successfully and email sent to the User", userService.createUser(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUserData() {
        List<User> usersData = userService.getAllUserData();
        System.out.println(usersData.toString());
        ResponseDTO responseDTO = new ResponseDTO("Users: ", usersData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getBy")
    public ResponseEntity<ResponseDTO> getUserData(@RequestHeader String token) {
        long Userid = jwtToken.decodeToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Data retrieved successfully for the ID: " + Userid, userService.getUserDataByToken(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateUserRegistrationData(@RequestHeader String token, @Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Updated User Data : ", userService.updateUserRegistration(token, userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUserRegistrationData(@RequestHeader String token) {
        userService.deleteUser(token);
        ResponseDTO responseDTO = new ResponseDTO("User Registration Data Deleted Successfully", "Deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestParam String email, @RequestParam("password") String password) {
        ResponseDTO responseDTO = new ResponseDTO("Login Successful", userService.userLogin(email, password));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/forgotPassword/{userId}")
    public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable("userId") Long userId, @RequestParam("email") String email) {
        User user = userService.forgotPassword(userId, email);
        ResponseDTO responseDTO = new ResponseDTO("User Data For " + userId + " :", user);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}