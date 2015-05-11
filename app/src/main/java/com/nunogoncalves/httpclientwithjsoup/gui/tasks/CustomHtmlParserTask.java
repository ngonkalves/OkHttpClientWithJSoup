package com.nunogoncalves.httpclientwithjsoup.gui.tasks;

import com.nunogoncalves.httpclientwithjsoup.parsers.HtmlParser;

/**
 * Created by nuno on 11-05-2015.
 */
class CustomHtmlParserTask extends AbstractTask<Void, String> {

    private final HtmlParser htmlParser;

    CustomHtmlParserTask(HtmlParser htmlParser) {
        super();
        this.htmlParser = htmlParser;
    }

    @Override
    protected String executeTask(Void... params) throws Exception {
        return this.htmlParser.parse();
    }
}
