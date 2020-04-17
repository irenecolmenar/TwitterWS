package com.twitter.TwitterWS.service.interfaces;

import com.twitter.TwitterWS.persistence.model.Tweet;
import twitter4j.Status;

import java.util.List;

public interface TweetInterface {
    void createTweet(final Status status) throws Exception;

    List<Tweet> getTweetsByUsername(final String username) ;

    List<Tweet> getTweets();

    List<Tweet> getValidatedTweets();

    List<Tweet> getValidatedTweetsByUsername(final String username);

    List<String> topTenHashtags();

    void validateTweet(final Integer idTweet);
}
