package com.uzabase.rsseater.feeds;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Moath
 */
public class Feeds {

    private final static String TAG_NAME_TITLE = "title";
    private final static String AG_NAME_LINK = "link";
    private final static String TAG_NAME_DESCRIPTION = "description";
    private final static String TAG_NAME_LAST_BUILD_DATE = "lastBuildDate";
    private final static String TAG_NAME_DOCS = "docs";
    private final static String TAG_NAME_GENERATOR = "generator";

    private String title;
    private String link;
    private String description;
    private String lastBuildDate;
    private String docs;
    private String generator;

    private List<FeedItem> items;

    private Document doc;

    public Feeds(Document xmlFeeds) {
        doc = xmlFeeds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public void setItems(List<FeedItem> items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public String getDocs() {
        return docs;
    }

    public String getGenerator() {
        return generator;
    }

    public List<FeedItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return convertDocumentToString();
    }

    private String convertDocumentToString() {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        String output = null;
        try {
            transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            output = writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return output;
    }
}
