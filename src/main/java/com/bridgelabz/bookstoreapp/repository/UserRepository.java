package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user_registration where email = :email and password = :password", nativeQuery = true)
    User loginValidation(String email, String password);

    @Query(value = "select * from user_registration where email = :email", nativeQuery = true)
    User getUserByEmail(String email);
}