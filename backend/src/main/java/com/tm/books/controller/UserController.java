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
import com.tm.books.model.User;
import com.tm.books.service.api.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@JsonView(Views.User.class)
	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<User> getUser(@PathVariable(value = "userId") Integer userId) {
		try {
			User user = userService.getUserById(userId);
			if (user == null) {
				log.error("Nem létezik felhasználó ezzel az id-vel: {}", "/api/user/" + userId);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return ResponseEntity.ok(user);
		} catch (InternalServerErrorException e) {
			log.error("Nem sikerült a felhasználó lekérdezése: {}", "/api/user/" + userId);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (BadRequestException e) {
			log.error("Nem sikerült a felhasználó lekérdezése: {}", "/api/user/" + userId);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			log.error("Nem sikerült a felhasználó lekérdezése: {}", "/api/user/" + userId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
