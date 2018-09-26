package com.tm.books.repository;

import org.springframework.data.repository.CrudRepository;

import com.tm.books.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
