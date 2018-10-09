package com.tm.books.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public List<Book> getOrderedBooks(Integer page, Integer count, String order) {
		Page<Book> pageBook = null;
		if (order.equals("dateAsc")) {
			Pageable pageable = PageRequest.of(page, count, Sort.by("id").ascending());
			pageBook = bookRepository.findAll(pageable);
		} else if (order.equals("dateDesc")) {
			Pageable pageable = PageRequest.of(page, count, Sort.by("id").descending());
			pageBook = bookRepository.findAll(pageable);
		}
		return pageBook.getContent();
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
