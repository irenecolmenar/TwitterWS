package com.twitter.TwitterWS;

import com.twitter.TwitterWS.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class TwitterWsApplication implements CommandLineRunner {

	@Autowired
	private TwitterService twitterService;

	public static void main(String[] args) {
		SpringApplication.run(TwitterWsApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		twitterService.searchForTweets();
	}

}
