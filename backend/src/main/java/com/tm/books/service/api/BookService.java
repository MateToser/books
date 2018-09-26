package com.tm.books.service.api;

import com.tm.books.dto.BookDTO;
import com.tm.books.model.Book;

public interface BookService {

	Book getBookById(Integer bookId);

	void saveBook(BookDTO bookDTO);

	Boolean approveBook(Integer userId, Integer bookId);

}
