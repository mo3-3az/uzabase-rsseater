package com.uzabase.rsseater.process;

import com.uzabase.rsseater.config.Config;
import org.apache.log4j.Logger;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * This is the main implementation of the feeds processors.
 * This processor utilizes StAX implementations, XMLEventReader and XMLEventWriter.
 *
 * <p>
 * There are three issues with these implementations, however two of these issues were resolved.
 * <ol>
 * <li>Issue#1: Doesn't encode html properly. (resolved).</li>
 * <li>Issue#2: Doesn't maintain the attributes order.</li>
 * <li>Issue#3: Will always create an end tag even if the tag is empty.</li>
 * </ol>
 *
 * @author Moath
 */
public class XmlFeedsProcessor implements FeedsProcessor {

    private final static Logger logger = Logger.getLogger(XmlFeedsProcessor.class);

    private static final String EMPTY_STRING = "";

    private Config config;

    @Override
    public String process(InputStream xmlFeeds, Config config) {
        if (xmlFeeds == null) {
            logger.error("Input stream is null, no processing will take place!");
            return null;
        }


        this.config = config;
        String processPhrase = config.caseSensitive() ? config.getProcessPhrase() : "(?i)" + config.getProcessPhrase();
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
                if (notFieldOfInterest(lastProcessedTag, xmlEvent)) {
                    writer.add(xmlEvent);
                    lastProcessedTag = xmlEvent.isStartElement() ? xmlEvent.asStartElement().getName().toString() : EMPTY_STRING;
                    continue;
                }

                String data = xmlEvent.asCharacters().getData().replaceAll(processPhrase, EMPTY_STRING);
                final Characters characters = eventFactory.createCharacters(data);
                switch (data) {
                    case "\"": //Check issue #1
                        xmlStringWriter.write("&quot;");
                        break;

                    case "'": //Check issue #1
                        xmlStringWriter.write("&#39;");
                        break;

                    default:
                        writer.add(characters);
                        break;
                }
            }

            xmlStringWriter.flush();
        } catch (XMLStreamException e) {
            logger.error("Error while processing xml feeds!", e);
        } finally {
            try {
                xmlStringWriter.close();
                xmlFeeds.close();
            } catch (IOException e) {
                logger.error("Error while closing streams!", e);
            }
        }

        return xmlStringWriter.toString();
    }

    private boolean notFieldOfInterest(String lastProcessedTag, XMLEvent xmlEvent) {
        return !xmlEvent.isCharacters() || !config.getFieldsToProcess().contains(lastProcessedTag);
    }

}
