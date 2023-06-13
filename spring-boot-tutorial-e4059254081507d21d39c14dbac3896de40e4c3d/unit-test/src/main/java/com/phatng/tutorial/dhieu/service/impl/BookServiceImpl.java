package com.phatng.tutorial.dhieu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phatng.tutorial.dhieu.entity.Book;
import com.phatng.tutorial.dhieu.exception.BookNotFoundException;
import com.phatng.tutorial.dhieu.repository.BookRepository;
import com.phatng.tutorial.dhieu.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Book create(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book update(Long id, Book book) {
    Book book1 = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    book1.setName(book.getName());
    return bookRepository.save(book1);
//    return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
  }


  @Override
  public List<Book> getAll() {

    return bookRepository.findAll();
  }

  @Override
  public Book getOne(Long id) {

    return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
  }
}
