package com.nunogoncalves.httpclientwithjsoup.gui.tasks;

import com.nunogoncalves.httpclientwithjsoup.requests.HttpCall;

/**
 * Created by nuno on 11-05-2015.
 */
class CustomHttpCallTask extends AbstractTask<Void, String> {

    private final HttpCall httpCall;

    CustomHttpCallTask(HttpCall httpCall) {
        super();
        this.httpCall = httpCall;
    }

    @Override
    protected String executeTask(Void... params) throws Exception {
        return httpCall.action();
    }
}
