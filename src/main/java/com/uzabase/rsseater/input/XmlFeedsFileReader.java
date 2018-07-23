package com.uzabase.rsseater.input;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Moath
 */
public class XmlFeedsFileReader implements FeedsReader {

    private final static Logger logger = Logger.getLogger(XmlFeedsFileReader.class);

    public Document read(String feedsUri) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(getClass().getResourceAsStream("/feeds.xml"));
        } catch (ParserConfigurationException | UnsupportedEncodingException e) {
            logger.error("Error while creating xml document builder.", e);
        } catch (IOException | SAXException e) {
            logger.error("Error while parsing xml document.", e);
        }

        logger.info("");
        return doc;
    }
}
