package com.phatng.tutorial.dhieu.repository;

import com.phatng.tutorial.dhieu.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private BookRepository repository;

  @Test
  public void testSaveMethod() {
    Book mockBook = new Book("test");
    mockBook = entityManager.persist(mockBook);
    entityManager.flush();
    Book bookSaved = repository.save(mockBook);
    Assertions.assertEquals(mockBook.getName()+"ngu", bookSaved.getName());

  }

  @Test
  public void testFindAll() {
    List<Book> mockBooks = new ArrayList<>();
    mockBooks.add(new Book("book1"));
    mockBooks.add(new Book("book2"));
    mockBooks.add(new Book("book3"));
    for (Book mockBook : mockBooks) {
      entityManager.persist(mockBook);
    }
    entityManager.flush();
    List<Book> booksFound = repository.findAll();
    Assertions.assertEquals(mockBooks.size(), booksFound.size());
//    for (int i = 0; i < mockBooks.size(); i++) {
//      Assertions.assertEquals(mockBooks.get(i).getName(), booksFound.get(i).getName());
//    }
    Assertions.assertArrayEquals(new List[]{mockBooks}, new List[]{booksFound});
  }

  @Test
  public void testDelete() {
    List<Book> mockBooks = new ArrayList<>();
    mockBooks.add(new Book("book1"));
    mockBooks.add(new Book("book2"));
    mockBooks.add(new Book("book3"));
    for (Book mockBook : mockBooks) {
      entityManager.persist(mockBook);
    }
    entityManager.flush();

    Long idToDelete = mockBooks.get(1).getId();
    repository.deleteById(idToDelete);

    List<Book> booksFound = repository.findAll();
    Assertions.assertEquals(mockBooks.size() -1, booksFound.size());

  }
}
