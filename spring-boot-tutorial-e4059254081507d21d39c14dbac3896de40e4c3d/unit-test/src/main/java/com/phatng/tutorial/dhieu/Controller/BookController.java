package com.phatng.tutorial.dhieu.Controller;

import com.phatng.tutorial.dhieu.entity.Book;
import com.phatng.tutorial.dhieu.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping("/create")
  public Book create(@RequestBody Book book) {
    return bookService.create(book);
  }
}
