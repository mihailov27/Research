package com.mmihaylov.research.crawler;

/**
 * Created by mmihaylov on 3/20/16.
 */
public class CrawlerException extends Exception {

    public CrawlerException(String message) {
        super(message);
    }

    public CrawlerException(Throwable throwable) {
        super(throwable);
    }

    public CrawlerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
