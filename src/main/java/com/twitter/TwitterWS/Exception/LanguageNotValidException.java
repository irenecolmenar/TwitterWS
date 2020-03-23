package com.twitter.TwitterWS.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LanguageNotValidException extends RuntimeException {
    public LanguageNotValidException(String language) {
        super(String.format("Language '%s' is not allowed", language));
    }
}
