package com.tm.books.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tm.books.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

	Optional<Author> findByName(String authorName);

}
