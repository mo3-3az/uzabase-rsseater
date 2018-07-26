package com.uzabase.rsseater.input;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

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

        } catch (UnsupportedEncodingException e) {
            logger.error("Error while creating xml document builder.", e);
        } catch (IOException e) {
            logger.error("Error while parsing xml document.", e);
        }

        logger.info("");
        return null;
    }
}
