package com.twitter.TwitterWS.persistence.repository;

import com.twitter.TwitterWS.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
