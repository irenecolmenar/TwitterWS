package com.twitter.TwitterWS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotValidException extends RuntimeException {
    public UserNotValidException(String language) {
        super(String.format("User '%s' does not exist or it doesn't have enough followers", language));
    }
}
