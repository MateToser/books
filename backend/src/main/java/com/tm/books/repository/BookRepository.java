package com.tm.books.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tm.books.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {

}
