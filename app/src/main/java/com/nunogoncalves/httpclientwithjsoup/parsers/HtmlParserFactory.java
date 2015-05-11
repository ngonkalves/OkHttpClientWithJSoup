package com.nunogoncalves.httpclientwithjsoup.parsers;

/**
 * Created by nuno on 11-05-2015.
 */
public class HtmlParserFactory {

    public HtmlParserFactory() {
        super();
    }

    public HtmlParser createParser(String html, String cssQuery) {
        return new CustomParser(html, cssQuery);
    }

}
