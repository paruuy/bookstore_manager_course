package com.pablo.bookstoremanager.service;

import com.pablo.bookstoremanager.dto.MessageResponseDTO;
import com.pablo.bookstoremanager.entity.Book;
import com.pablo.bookstoremanager.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(Book book){
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Book created with ID: " + savedBook.getId())
                .build();
    }
}
