package com.tm.books.service.impl;

import org.springframework.stereotype.Service;

import com.tm.books.repository.UserRepository;
import com.tm.books.service.api.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

}
