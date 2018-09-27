package com.tm.books.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tm.books.dto.BookDTO;
import com.tm.books.model.Author;
import com.tm.books.model.Book;
import com.tm.books.model.User;
import com.tm.books.repository.AuthorRepository;
import com.tm.books.repository.BookRepository;
import com.tm.books.repository.UserRepository;
import com.tm.books.service.api.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final UserRepository userRepository;

	@Override
	public Book getBookById(Integer bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			return book.get();
		}
		return null;
	}

	@Override
	public void saveBook(BookDTO bookDTO) {
		Optional<User> user = userRepository.findById(bookDTO.getUserId());
		if (!user.isPresent()) {
			return;
		}
		Optional<Author> author = authorRepository.findByName(bookDTO.getAuthorName());
		if (!author.isPresent()) {
			return;
		}
		Book book = new Book(null, bookDTO.getBookTitle(), bookDTO.getBookDescription(), bookDTO.getBookPrice(),
				bookDTO.getBookRating(), false, author.get(), user.get());
		bookRepository.save(book);
	}

	@Override
	public Boolean approveBook(Integer userId, Integer bookId) {
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			Optional<User> user = userRepository.findById(userId);
			Boolean approved = book.getApproved();
			if (user.isPresent() && user.get().getApprover()) {
				book.setApproved(!approved);
				bookRepository.save(book);
				return !approved;
			}
		}
		return null;
	}

}
