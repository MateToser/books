package com.tm.books.repository;

import org.springframework.data.repository.CrudRepository;

import com.tm.books.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
