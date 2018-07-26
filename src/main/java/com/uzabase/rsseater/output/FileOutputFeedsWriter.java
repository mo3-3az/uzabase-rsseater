package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Moath
 */
public class FileOutputFeedsWriter implements FeedsWriter {

    private final static Logger logger = Logger.getLogger(FileOutputFeedsWriter.class);

    public void write(String feeds) {
        try {
            FileWriter fileWriter = new FileWriter(new File("output.xml"));
            fileWriter.write(feeds);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error("Error while writing feeds to file.");
        }
    }
}
