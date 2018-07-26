package com.uzabase.rsseater.convert;

import com.uzabase.rsseater.config.Config;
import org.apache.log4j.Logger;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author Moath
 */
public class XmlFeedsConverter implements FeedsConverter {

    private final static Logger logger = Logger.getLogger(XmlFeedsConverter.class);
    private static final String EMPTY_STRING = "";

    private Config config;
    private String processPhrase;

    public XmlFeedsConverter(Config config) {
        this.config = config;
        processPhrase = config.caseSensitive() ? config.getProcessPhrase() : "(?i)" + config.getProcessPhrase();
    }

    @Override
    public String convert(InputStream xmlFeeds) {
        final StringWriter xmlStringWriter = new StringWriter();
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inputFactory.createXMLEventReader(xmlFeeds);

            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLEventWriter writer = outputFactory.createXMLEventWriter(xmlStringWriter);

            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            String lastProcessedTag = EMPTY_STRING;
            while (reader.hasNext()) {
                final XMLEvent xmlEvent = reader.nextEvent();
                if (isNotFieldOfIntrest(lastProcessedTag, xmlEvent)) {
                    writer.add(xmlEvent);
                    lastProcessedTag = xmlEvent.isStartElement() ? xmlEvent.asStartElement().getName().toString() : EMPTY_STRING;
                    continue;
                }

                String data = xmlEvent.asCharacters().getData();
                final Characters characters = eventFactory.createCharacters(data.replaceAll(processPhrase, EMPTY_STRING));

                if (data.equals("\"")) {
                    characters.writeAsEncodedUnicode(xmlStringWriter);
                } else if (data.equals("'")) {
                    xmlStringWriter.write("&#39;");
                } else {
                    writer.add(characters);
                }
            }

            xmlStringWriter.flush();
        } catch (XMLStreamException e) {
            logger.error("Error while converting xml feeds!", e);
        }

        return xmlStringWriter.toString();
    }

    private boolean isNotFieldOfIntrest(String lastProcessedTag, XMLEvent xmlEvent) {
        return !xmlEvent.isCharacters() || !config.getFieldsToProcess().contains(lastProcessedTag);
    }

}
