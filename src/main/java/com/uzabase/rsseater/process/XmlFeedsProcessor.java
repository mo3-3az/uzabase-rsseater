package com.uzabase.rsseater.process;

import com.uzabase.rsseater.config.Config;
import com.uzabase.rsseater.output.FileOutputFeedsWriter;
import com.uzabase.rsseater.output.StandardOutputFeedsWriter;
import org.apache.log4j.Logger;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This is the main implementation of the feeds processors.
 * This processor utilizes StAX implementations, XMLEventReader and XMLEventWriter.
 *
 * <p>
 * There are three issues with these implementations, however one is resolved the other two are
 * irrelevant since they don't affect the structure of the xml document.
 *
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
    private static final String ENCODED_QUOTE = "&quot;";
    private static final String ENCODED_SINGLE_QUOTE = "&#39;";

    private Config config;

    @Override
    public void process(InputStream xmlFeeds, Config config) {
        if (xmlFeeds == null) {
            logger.error("Input stream is null, no processing will take place!");
            return;
        }

        this.config = config;
        String processPhrase = config.caseSensitive() ? config.getProcessPhrase() : "(?i)" + config.getProcessPhrase();

        XMLEventWriter stdOutputXmlEventWriter = null;
        XMLEventWriter fileXmlEventWriter = null;
        XMLEventReader reader = null;
        StandardOutputFeedsWriter stdOutputFeedsWriter;
        FileOutputFeedsWriter fileOutputFeedsWriter;

        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLEventReader(xmlFeeds);

            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            stdOutputFeedsWriter = new StandardOutputFeedsWriter();
            stdOutputXmlEventWriter = outputFactory.createXMLEventWriter(stdOutputFeedsWriter);
            fileOutputFeedsWriter = new FileOutputFeedsWriter();
            fileXmlEventWriter = outputFactory.createXMLEventWriter(fileOutputFeedsWriter);

            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            String lastProcessedTag = EMPTY_STRING;
            while (reader.hasNext()) {
                final XMLEvent xmlEvent = reader.nextEvent();
                if (notEventOfInterest(lastProcessedTag, xmlEvent)) {
                    stdOutputXmlEventWriter.add(xmlEvent);
                    fileXmlEventWriter.add(xmlEvent);
                    lastProcessedTag = xmlEvent.isStartElement() ? xmlEvent.asStartElement().getName().toString() : EMPTY_STRING;
                    continue;
                }

                String data = xmlEvent.asCharacters().getData().replaceAll(processPhrase, EMPTY_STRING);
                final Characters characters = eventFactory.createCharacters(data);
                switch (data) {
                    case "\"": //Check issue #1
                        stdOutputFeedsWriter.write(ENCODED_QUOTE);
                        fileOutputFeedsWriter.write(ENCODED_QUOTE);
                        break;

                    case "'": //Check issue #1
                        stdOutputFeedsWriter.write(ENCODED_SINGLE_QUOTE);
                        fileOutputFeedsWriter.write(ENCODED_SINGLE_QUOTE);
                        break;

                    default:
                        fileXmlEventWriter.add(characters);
                        stdOutputXmlEventWriter.add(characters);
                        break;
                }
            }

        } catch (XMLStreamException e) {
            logger.error("Error while processing xml feeds!", e);
        } catch (FileNotFoundException e) {
            logger.error("Error while creating file output stream xml feeds!", e);
        } finally {
            closeStreams(reader, stdOutputXmlEventWriter, fileXmlEventWriter);
        }
    }

    private void closeStreams(XMLEventReader xMLEventReader, XMLEventWriter stdOutputXmlEventWriter, XMLEventWriter fileXmlEventWriter) {
        try {
            if (stdOutputXmlEventWriter != null) {
                stdOutputXmlEventWriter.flush();
                stdOutputXmlEventWriter.close();
            }

            if (fileXmlEventWriter != null) {
                fileXmlEventWriter.flush();
                fileXmlEventWriter.close();
            }

            xMLEventReader.close();
        } catch (XMLStreamException e) {
            logger.error("Error while closing xml event streams!", e);
        }
    }

    private boolean notEventOfInterest(String lastProcessedTag, XMLEvent xmlEvent) {
        return !xmlEvent.isCharacters() || !config.getFieldsToProcess().contains(lastProcessedTag);
    }

}
