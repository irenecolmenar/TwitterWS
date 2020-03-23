package com.twitter.TwitterWS.service.interfaces;

import com.twitter.TwitterWS.persistence.model.Tweet;

import java.util.List;

public interface TweetInterface {
    Integer createTweet(final String text,
                        final String username,
                        final String location) throws Exception;

    List<Tweet> getTweetsByUsername(final String username) ;

    List<Tweet> getTweets();

    List<Tweet> getValidatedTweets();

    List<String> topTenHashtags();

    void validateTweet(final Integer idTweet);
}
