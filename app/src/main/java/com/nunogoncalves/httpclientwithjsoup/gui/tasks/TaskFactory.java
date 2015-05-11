package com.nunogoncalves.httpclientwithjsoup.gui.tasks;

import android.os.AsyncTask;

import com.nunogoncalves.httpclientwithjsoup.parsers.HtmlParserFactory;
import com.nunogoncalves.httpclientwithjsoup.requests.HttpCallFactory;

/**
 * Created by nuno on 11-05-2015.
 */
public class TaskFactory {

    private final HttpCallFactory callFactory;
    private final HtmlParserFactory htmlParserFactory;

    public TaskFactory() {
        super();
        this.callFactory = new HttpCallFactory();
        this.htmlParserFactory = new HtmlParserFactory();
    }

    public AsyncTask<Void, Integer, String> createCustomHttpCallTask(final String url) {
        return createCustomHttpCallTask(url, null);
    }

    public AsyncTask<Void, Integer, String> createCustomHttpCallTask(final String url, final ProgressUpdater progressUpdater) {
        CustomHttpCallTask customHttpCallTask = new CustomHttpCallTask(callFactory.createCall(url));
        customHttpCallTask.setProgressUpdater(progressUpdater);
        return customHttpCallTask;
    }

    public AsyncTask<Void, Integer, String> createCustomHtmlParserTask(final String html, final String cssQuery) {
        return createCustomHtmlParserTask(html, cssQuery, null);
    }

    public AsyncTask<Void, Integer, String> createCustomHtmlParserTask(final String html, final String cssQuery, final ProgressUpdater progressUpdater) {
        CustomHtmlParserTask customHtmlParserTask = new CustomHtmlParserTask(htmlParserFactory.createParser(html, cssQuery));
        customHtmlParserTask.setProgressUpdater(progressUpdater);
        return customHtmlParserTask;
    }
}
