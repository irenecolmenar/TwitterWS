package com.twitter.TwitterWS.service;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.twitter.TwitterWS.Exception.LanguageNotValidException;
import com.twitter.TwitterWS.Exception.UserNotValidException;
import com.twitter.TwitterWS.persistence.model.Hashtag;
import com.twitter.TwitterWS.persistence.model.Tweet;
import com.twitter.TwitterWS.persistence.model.User;
import com.twitter.TwitterWS.persistence.repository.HashtagRepository;
import com.twitter.TwitterWS.persistence.repository.TweetRepository;
import com.twitter.TwitterWS.persistence.repository.UserRepository;
import com.twitter.TwitterWS.service.interfaces.TweetInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TweetService implements TweetInterface {

    private final Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
    private final int MIN_FOLLOWERS = 1500;
    private final List<String> ALLOWED_LANGUAGES = Arrays.asList("es", "it", "fr");
    private final int NUM_TOP_HASHTAGS = 10;

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public Integer createTweet(final String text,
                               final String username,
                               final String location) throws Exception {

        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty() || optUser.get().getFollowers() < MIN_FOLLOWERS) {
            throw new UserNotValidException(username);
        }

        storeHashTags(text);
        validLanguage(text);

        Tweet t = Tweet.builder()
                .text(text)
                .user(optUser.get())
                .location(location)
                .created_at(new Date())
                .build();

        tweetRepository.save(t);

        return t.getId();
    }

    @Override
    public List<Tweet> getTweetsByUsername(final String username) {
        Optional<User> optU = userRepository.findByUsername(username);
        if (optU.isPresent())
            return tweetRepository.findAllByUser(optU.get());
        else {
            throw new UserNotValidException(username);
        }
    }

    @Override
    public List<Tweet> getTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public List<Tweet> getValidatedTweets() {
        return tweetRepository.findAllByValidatedTrue();
    }

    @Override
    public List<String> topTenHashtags() {
        return hashtagRepository.findAllByOrderByCounterDesc(PageRequest.of(0, NUM_TOP_HASHTAGS))
                .stream().map(Hashtag::getText).collect(Collectors.toList());
    }

    @Override
    public void validateTweet(final Integer idTweet) {
        Optional<Tweet> optT = tweetRepository.findById(idTweet);
        if (optT.isPresent()) {
            Tweet t = optT.get();
            t.setValidated(true);
            tweetRepository.save(t);
        }
    }

    public List<String> getHashtags (String text) {
        Matcher mat = MY_PATTERN.matcher(text);
        List<String> hashtags = new ArrayList<>();
        while(mat.find()) {
            String hashtag = mat.group(1);
            hashtags.add(hashtag);
        }
        return hashtags;
    }

    public void storeHashTags(String text) {
        List<String> newHashtags = getHashtags(text);
        newHashtags.forEach(hashtag -> {
                    Optional<Hashtag> opth = hashtagRepository.findHashtagByText(hashtag);
                    Hashtag h;
                    if (opth.isPresent()) {
                        h = opth.get();
                        h.setCounter(h.getCounter() + 1);
                    } else {
                        h = Hashtag.builder()
                                .text(hashtag)
                                .counter(1)
                                .build();
                    }
                    hashtagRepository.save(h);
                });
    }

    public void validLanguage(String text) throws APIError {
        DetectLanguage.apiKey = "472004cb05be5542014a4bff177c2f89";
        List<Result> languages = DetectLanguage.detect(text);
        for (Result l: languages) {
            if (!ALLOWED_LANGUAGES.contains(l.language)) {
                throw new LanguageNotValidException(l.language);
            }
        }
    }
}
