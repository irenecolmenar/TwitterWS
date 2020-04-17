package com.twitter.TwitterWS.persistence.repository;

import com.twitter.TwitterWS.persistence.model.Tweet;
import com.twitter.TwitterWS.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TweetRepository extends CrudRepository<Tweet, Integer> {
    List<Tweet> findAll();
    List<Tweet> findAllByUser(User user);
    List<Tweet> findAllByValidatedTrue();
    List<Tweet> findAllByValidatedTrueAndUser(User u);

}
