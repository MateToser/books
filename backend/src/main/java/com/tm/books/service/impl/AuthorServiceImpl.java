package com.tm.books.service.impl;

import org.springframework.stereotype.Service;

import com.tm.books.repository.AuthorRepository;
import com.tm.books.service.api.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

}
