package com.application.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
