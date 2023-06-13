package com.phatng.tutorial.dhieu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phatng.tutorial.dhieu.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
