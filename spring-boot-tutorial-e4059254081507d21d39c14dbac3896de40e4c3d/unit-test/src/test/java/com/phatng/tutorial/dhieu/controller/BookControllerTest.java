package com.phatng.tutorial.dhieu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phatng.tutorial.dhieu.Controller.BookController;
import com.phatng.tutorial.dhieu.entity.Book;
import com.phatng.tutorial.dhieu.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BookControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private BookController bookController;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private BookService bookService;

  @Test
  public void test_create_WhenInputValid_Return201AndResponseBody() throws Exception {
    Book mockBook = new Book( "Test");
    Mockito.when(bookService.create(mockBook)).thenReturn(mockBook);

    MvcResult mvcResult = mockMvc.perform(
                post("/api/v1/books/create")
                      .contentType("application/json")
                      .content(objectMapper.writeValueAsString(mockBook)))
          .andReturn();
    String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
    Assertions.assertEquals(responseBody, objectMapper.writeValueAsString(bookController.create(mockBook)));


  }


}
