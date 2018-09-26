package com.tm.books.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tm.books.model.Author;
import com.tm.books.repository.AuthorRepository;
import com.tm.books.service.api.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	@Override
	public Author getAuthorById(Integer authorId) {
		Optional<Author> author = authorRepository.findById(authorId);
		if (author.isPresent()) {
			return author.get();
		}
		return null;
	}

}
