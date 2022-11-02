package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartDTO {
    public long bookId;
    public long quantity;
}