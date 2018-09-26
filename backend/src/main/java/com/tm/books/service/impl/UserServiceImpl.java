package com.tm.books.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tm.books.model.User;
import com.tm.books.repository.UserRepository;
import com.tm.books.service.api.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUserById(Integer userId) {
		Optional<User> author = userRepository.findById(userId);
		if (author.isPresent()) {
			return author.get();
		}
		return null;
	}
}
