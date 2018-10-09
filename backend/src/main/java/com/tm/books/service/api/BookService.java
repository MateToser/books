package com.tm.books.service.api;

import java.util.List;

import com.tm.books.dto.BookDTO;
import com.tm.books.model.Book;

public interface BookService {

	Book getBookById(Integer bookId);

	List<Book> getOrderedBooks(Integer page, Integer count, String order);

	void saveBook(BookDTO bookDTO);

	Boolean approveBook(Integer userId, Integer bookId);

}
