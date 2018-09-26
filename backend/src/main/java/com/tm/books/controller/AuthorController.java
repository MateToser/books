package com.tm.books.controller;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.tm.books.common.Views;
import com.tm.books.model.Author;
import com.tm.books.service.api.AuthorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;

	@JsonView(Views.Author.class)
	@GetMapping("/{authorId}")
	@ResponseBody
	public ResponseEntity<Author> getAuthor(@PathVariable(value = "authorId") Integer authorId) {
		try {
			Author author = authorService.getAuthorById(authorId);
			if (author == null) {
				log.error("Nem létezik szerző ezzel az id-vel: {}", "/api/author/" + authorId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(author);
		} catch (InternalServerErrorException e) {
			log.error("Nem sikerült a szerző lekérdezése: {}", "/api/author/" + authorId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadRequestException e) {
			log.error("Nem sikerült a szerző lekérdezése: {}", "/api/author/" + authorId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			log.error("Nem sikerült a szerző lekérdezése: {}", "/api/author/" + authorId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
