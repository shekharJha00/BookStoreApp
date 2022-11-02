package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book addBook(BookDTO bookDTO) {
        return bookRepository.save(new Book(bookDTO));
    }
    @Override
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookStoreException("Book with id " + bookId + " not found"));
    }
    @Override
    public Book updateBookQuantity(Long bookId, int bookQuantity) {
        Book bookData = this.getBookById(bookId);
        bookData.setBookQuantity(bookQuantity);
        return bookRepository.save(bookData);
    }
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
