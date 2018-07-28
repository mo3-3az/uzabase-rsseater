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
        InputStream inputStream = null;
        try {
            client.executeMethod(method);
            inputStream = method.getResponseBodyAsStream();
            logger.info("Input stream was opened successfully. Uri: " + feedsUri);
        } catch (IOException e) {
            logger.error("Error while reading the xml stream! Feeds Uri: " + feedsUri, e);
        }

        return inputStream;
    }
}
