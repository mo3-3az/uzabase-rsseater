package com.uzabase.rsseater.input;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Moath
 */
public class XmlFeedsReader implements FeedsReader {

    private final static Logger logger = Logger.getLogger(XmlFeedsReader.class);

    public InputStream read(String feedsUri) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(feedsUri);
        try {
            client.executeMethod(method);
            return method.getResponseBodyAsStream();
        } catch (IOException e) {
            logger.fatal("Error while reading the xml stream! Feeds Uri: " + feedsUri, e);
            throw new RuntimeException("Error while reading the xml stream! Feeds Uri: " + feedsUri, e);
        }
    }
}
