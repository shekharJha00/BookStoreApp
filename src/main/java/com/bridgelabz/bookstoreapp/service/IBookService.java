package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.Book;

import java.util.List;

public interface IBookService {
    Book addBook(BookDTO bookDTO);
    List<Book> getBookList();
    Book getBookById(Long bookId);
    Book updateBookQuantity(Long bookId, int bookQuantity);
    void deleteBook(Long bookId);
}
