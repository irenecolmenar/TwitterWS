package com.twitter.TwitterWS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    private TweetService tweetService;

    private final int MIN_FOLLOWERS = 1500;
    private final String KEYWORD = "a";
    private final List<String> ALLOWED_LANGUAGES = Arrays.asList("es", "it", "fr");

    @Async
    public void searchForTweets() throws Exception {
        // The factory instance is re-useable and thread safe.
        Twitter twitter = TwitterFactory.getSingleton();

        Query query = new Query(KEYWORD);
        QueryResult result;
        do {
            try {
                result = twitter.search(query);
            } catch (Exception e) {
                Thread.sleep(1000000);
                result = twitter.search(query);
            }
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                if (tweet.getUser().getFollowersCount() >= MIN_FOLLOWERS
                        && validLanguage(tweet)) {
                    tweetService.createTweet(tweet);
                }
            }
            Thread.sleep(500);
        } while ((query = result.nextQuery()) != null);
    }

    public boolean validLanguage(Status st) {
        return ALLOWED_LANGUAGES.contains(st.getLang());
    }

}
