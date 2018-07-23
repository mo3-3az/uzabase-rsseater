package com.uzabase.rsseater.input;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Moath
 */
public class XmlFeedsReader implements FeedsReader {

    private final static Logger logger = Logger.getLogger(XmlFeedsReader.class);

    public Document read(String feedsUri) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(feedsUri);
        Document doc = null;
        try {
            client.executeMethod(method);
            InputStream responseBody = method.getResponseBodyAsStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(responseBody);
        } catch (ParserConfigurationException | UnsupportedEncodingException e) {
            logger.error("Error while creating xml document builder.", e);
        } catch (IOException | SAXException e) {
            logger.error("Error while parsing xml document.", e);
        }

        logger.info("");
        return doc;
    }
}
