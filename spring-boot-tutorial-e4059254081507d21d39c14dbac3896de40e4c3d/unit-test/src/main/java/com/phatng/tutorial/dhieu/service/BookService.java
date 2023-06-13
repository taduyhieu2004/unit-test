package com.phatng.tutorial.dhieu.service;

import java.util.List;

import com.phatng.tutorial.dhieu.entity.Book;

public interface BookService {
  Book create(Book book);

  Book update(Long id, Book book);

  List<Book> getAll();

  Book getOne(Long id);

}