package com.tm.books.controller;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tm.books.common.Views;
import com.tm.books.dto.BookDTO;
import com.tm.books.model.Book;
import com.tm.books.service.api.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@JsonView(Views.Book.class)
	@GetMapping("/{bookId}")
	@ResponseBody
	public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") Integer bookId) {
		try {
			Book book = bookService.getBookById(bookId);
			if (book == null) {
				log.error("Nem létezik könyv ezzel az id-vel: {}", "/api/book/" + bookId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(book);
		} catch (InternalServerErrorException e) {
			log.error("Nem sikerült könyv lekérdezése: {}", "/api/book/" + bookId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadRequestException e) {
			log.error("Nem sikerült könyv lekérdezése: {}", "/api/book/" + bookId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			log.error("Nem sikerült könyv lekérdezése: {}", "/api/book/" + bookId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/addbook")
	@ResponseBody
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
		try {
			bookService.saveBook(bookDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (InternalServerErrorException e) {
			log.error("Nem sikerült a könyv hozzáadása: {}", "/api/book/addbook");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadRequestException e) {
			log.error("Nem sikerült a könyv hozzáadása: {}", "/api/book/addbook");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			log.error("Nem sikerült a könyv hozzáadása: {}", "/api/book/addbook");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/approve/{userId}/{bookId}")
	@ResponseBody
	public ResponseEntity<Boolean> approveBook(@PathVariable(value = "userId") Integer userId,
			@PathVariable(value = "bookId") Integer bookId) {
		try {
			Boolean approved = bookService.approveBook(userId, bookId);
			return ResponseEntity.ok(approved);
		} catch (InternalServerErrorException e) {
			log.error("Nem sikerült a könyvet elfogadni: {}", "/api/book/approve/" + userId + "/" + bookId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadRequestException e) {
			log.error("Nem sikerült a könyvet elfogadni: {}", "/api/book/approve/" + userId + "/" + bookId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			log.error("Nem sikerült a könyvet elfogadni: {}", "/api/book/approve/" + userId + "/" + bookId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
