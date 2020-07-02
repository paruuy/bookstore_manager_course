package com.pablo.bookstoremanager.service;

import com.pablo.bookstoremanager.dto.BookDTO;
import com.pablo.bookstoremanager.dto.MessageResponseDTO;
import com.pablo.bookstoremanager.entity.Book;
import com.pablo.bookstoremanager.exception.BookNotFoundException;
import com.pablo.bookstoremanager.mapper.BookMapper;
import com.pablo.bookstoremanager.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {
    private final BookMapper bookMapper =  BookMapper.INSTANCE;
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(BookDTO bookDTO){
        Book bookToSave = bookMapper.toModel(bookDTO);
        Book savedBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID: " + savedBook.getId())
                .build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toDTO(book);
    }
}
