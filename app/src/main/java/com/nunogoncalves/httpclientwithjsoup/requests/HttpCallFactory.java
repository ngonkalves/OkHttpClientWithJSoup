package com.nunogoncalves.httpclientwithjsoup.requests;

/**
 * Created by nuno on 11-05-2015.
 */
public class HttpCallFactory {

    public HttpCallFactory() {
        super();
    }

    public HttpCall createCall(String url) {
        return new CustomHttpCall(url);
    }

}
