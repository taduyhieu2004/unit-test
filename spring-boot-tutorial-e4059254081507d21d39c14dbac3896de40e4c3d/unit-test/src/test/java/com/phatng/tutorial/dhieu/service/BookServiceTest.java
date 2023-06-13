package com.phatng.tutorial.dhieu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.phatng.tutorial.dhieu.entity.Book;
import com.phatng.tutorial.dhieu.exception.BookNotFoundException;
import com.phatng.tutorial.dhieu.repository.BookRepository;
import com.phatng.tutorial.dhieu.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@WebMvcTest(BookService.class)
public class BookServiceTest {

  @MockBean
  BookRepository bookRepository;

  @Autowired
  BookService bookService;

  @Test
  void whenCreate_shouldReturnCreatedBook() {
    // 1. create mock data
    Book mockBook = new Book("Test Book");

    // 2. define behavior of Repository
    when(bookRepository.save(mockBook)).thenReturn(mockBook);

    // 3. call service method
    Book createdBook = bookService.create(mockBook);

    // 4. assert the result
    assertThat(createdBook).isEqualTo(mockBook);

    // 4.1 ensure repository is called
    verify(bookRepository).save(mockBook);
  }

  @Test
  void whenUpdate_shouldReturnUpdatedBook() {
    // 1. create mock data
    Book mockBook = new Book("Test Book");
    mockBook.setId(1L);

    // 2. define behavior of Repository
    when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.of(mockBook));
    when(bookRepository.save(Mockito.any(Book.class))).thenReturn(mockBook);

    // 3. call service method
    Book updatedBook = bookService.update(1L, mockBook);

    // 4. assert the result
    assertThat(updatedBook).isEqualTo(mockBook);

    // 4.1 ensure repository is called
    verify(bookRepository).findById(mockBook.getId());
    verify(bookRepository).save(Mockito.any(Book.class));
  }

  @Test
  void whenGetAll_shouldReturnList() {
    // 1. create mock data
    List<Book> mockBooks = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      mockBooks.add(new Book((long) i));
    }

    // 2. define behavior of Repository
    when(bookRepository.findAll()).thenReturn(mockBooks);

    // 3. call service method
    List<Book> actualBooks = bookService.getAll();

    // 4. assert the result
    assertThat(actualBooks.size()).isEqualTo(mockBooks.size());

    // 4.1 ensure repository is called
    verify(bookRepository).findAll();
  }

  @Test
  void whenGetInvalidOne_shouldThrowException() {
    Long invalidBookId = 7L;

    when(bookRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));

    assertThatThrownBy(() -> bookService.getOne(invalidBookId))
          .isInstanceOf(BookNotFoundException.class);

    verify(bookRepository).findById(any(Long.class));
  }

  @Test
  void whenGetOne() {
    Long bookId = 7L;
    Book mockBook = new Book("Test Book");
    when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(mockBook));
    assertThat(bookService.getOne(bookId)).isEqualTo(mockBook);

    verify(bookRepository).findById(any(Long.class));
  }
}
