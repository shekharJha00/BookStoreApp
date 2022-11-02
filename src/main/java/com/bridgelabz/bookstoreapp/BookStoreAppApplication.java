package com.bridgelabz.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@Slf4j
@SpringBootApplication
public class BookStoreAppApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BookStoreAppApplication.class, args);
        log.info("Book Store App is now Running", applicationContext.getEnvironment().getProperty("environment"));
    }
}