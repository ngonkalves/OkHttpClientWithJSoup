package com.nunogoncalves.httpclientwithjsoup.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by nuno on 10-05-2015.
 */
class CustomParser implements HtmlParser {

    private final String html;

    private final String cssQuery;

    CustomParser(String html, String cssQuery) {
        super();
        this.html = html;
        this.cssQuery = cssQuery;
    }

    private Elements parseElements() {
        Document doc = Jsoup.parse(html);

        return doc.select(cssQuery);
    }

    @Override
    public String parse() {
        Elements elements = parseElements();

        String output = new ElementsBeautifier(elements).beautify();

        return output;
    }

    private static class ElementsBeautifier {

        private final Elements elements;

        private final String lineSeparator;

        ElementsBeautifier(Elements elements) {
            super();
            this.elements = elements;
            this.lineSeparator = "\n\n\n";
        }

        private String buildOutput(Elements elements, String lineSeparator) {
            StringBuilder s = new StringBuilder();

            for (Element element : elements) {
                s.append(element.html()).append(lineSeparator);
            }

            return s.toString();
        }

        public String beautify() {
            return buildOutput(this.elements, this.lineSeparator);
        }
    }
}
