package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * File based implementation which will write the processed feeds to an xml file.
 *
 * @author Moath
 */
public class FileOutputFeedsWriter extends FileOutputStream {

    private final static Logger logger = Logger.getLogger(StandardOutputFeedsWriter.class);

    private static final String OUTPUT_XML = "output.xml";

    public FileOutputFeedsWriter() throws FileNotFoundException {
        super(OUTPUT_XML);
    }

    public void write(String data) {
        if (data == null) {
            return;
        }

        try {
            write(data.getBytes());
        } catch (IOException e) {
            logger.error("Error while writing bytes to file output stream!", e);
        }
    }
}
