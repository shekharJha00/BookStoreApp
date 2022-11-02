package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;


public @Data class BookDTO {
    public String bookName;
    public String bookAuthor;
    public String bookDescription;
    public String bookImage;
    public int bookPrice;
    public int bookQuantity;
}
