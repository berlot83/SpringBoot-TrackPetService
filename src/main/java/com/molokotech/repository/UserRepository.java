package com.molokotech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.molokotech.model.User;


public interface UserRepository extends MongoRepository<User, String> {
    
    User findByEmail(String email);
    
}
