package com.nunogoncalves.httpclientwithjsoup.requests;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by nuno on 10-05-2015.
 */
class CustomHttpCall implements HttpCall {

    private final OkHttpClient client;
    private final String url;

    public CustomHttpCall(String url) {
        super();
        this.client = new OkHttpClient();
        this.url = url;
    }

    private Response doHttpRequest() throws IOException {
        Request request = createRequest();

        Response response = call(request);

        return response;
    }

    private Request createRequest() {
        return new Request.Builder()
                .url(url)
                .build();
    }

    private Response call(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    private String getResponse(Response response) throws IOException {
        return response.body().string();
    }

    public String action() throws IOException {
        Response response = doHttpRequest();

        return getResponse(response);
    }
}
