package com.uzabase.rsseater.convert;

import com.uzabase.rsseater.feeds.Feeds;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.List;

/**
 * @author Moath
 */
public class XmlFeedsConverter implements FeedsConverter {

    private final static Logger logger = Logger.getLogger(XmlFeedsConverter.class);
    private static final String RSS_CHANNEL_ITEM = "/rss/channel/item";

    private List<String> fieldsToProcess;
    private String processPhrase;

    public XmlFeedsConverter(List<String> fieldsToProcess, String processPhrase) {
        this.fieldsToProcess = fieldsToProcess;
        this.processPhrase = processPhrase;
    }

    @Override
    public Feeds convert(Document xmlFeeds) {
        logger.info("");
        XPath xPath = XPathFactory.newInstance().newXPath();
        Feeds feeds = new Feeds(xmlFeeds);
        try {
            NodeList nodeList = (NodeList) xPath.compile(RSS_CHANNEL_ITEM).evaluate(xmlFeeds, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                fieldsToProcess.forEach(field -> {
                    try {
                        processFields((NodeList) xPath.compile("//" + field).evaluate(node, XPathConstants.NODESET));
                    } catch (XPathExpressionException e) {
                        logger.error("Error while converting xml feeds", e);
                    }
                });
            }
        } catch (XPathExpressionException e) {
            logger.error("Error while converting xml feeds", e);

        }
        return feeds;
    }

    private void processFields(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            node.setTextContent(node.getTextContent().replaceAll(processPhrase, ""));
        }
    }
}
