package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File based implementation which will write the processed feeds to an xml file.
 *
 * @author Moath
 */
public class FileOutputFeedsWriter implements FeedsWriter {

    static final String OUTPUT_XML = "output.xml";

    private final static Logger logger = Logger.getLogger(FileOutputFeedsWriter.class);

    public void write(String feeds) {
        if (feeds == null) {
            logger.error("Feeds string is null, no writing to file will take place!");
            return;
        }

        try {
            FileWriter fileWriter = new FileWriter(new File(OUTPUT_XML));
            fileWriter.write(feeds);
            fileWriter.flush();
            fileWriter.close();

            logger.info("Processed feeds were written to a file (" + OUTPUT_XML + ") successfully.");
        } catch (IOException e) {
            logger.error("Error while writing feeds to file.");
        }
    }
}
