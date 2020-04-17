package com.twitter.TwitterWS.controller;

import com.twitter.TwitterWS.persistence.model.Tweet;
import com.twitter.TwitterWS.service.TweetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Twitter management system")
@RequestMapping("tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping
    @ApiOperation(value = "get all tweets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved albums")})
    public List<Tweet> getTweets() {
        return tweetService.getTweets();
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "get all tweets of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tweets"),
            @ApiResponse(code = 400, message = "The user is not correct")})
    public List<Tweet> getTweetsByUsername(@PathVariable String username) {
        return tweetService.getTweetsByUsername(username);
    }

    @GetMapping("/validated")
    @ApiOperation(value = "Get all validated tweets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tweets")})
    public List<Tweet> getValidatedTweets() {
        return tweetService.getValidatedTweets();
    }

    @GetMapping ("/mostUsedHashtags")
    @ApiOperation(value = "Get the most used hashtags")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved tweets")})
    public List<String> mostUsedHashtags() {
        return tweetService.topTenHashtags();
    }

    @PostMapping("/validate")
    @ApiOperation(value = "Validates a tweet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully tweet validated")})
    public void validateTweet(@RequestParam final Integer idTweet) {
        tweetService.validateTweet(idTweet);
    }
}
