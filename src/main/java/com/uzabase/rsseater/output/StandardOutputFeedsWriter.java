package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Output stream based implementation which will write the processed feeds to standard output stream.
 *
 * @author Moath
 */
public class StandardOutputFeedsWriter extends PrintStream {

    private final static Logger logger = Logger.getLogger(StandardOutputFeedsWriter.class);

    public StandardOutputFeedsWriter() {
        super(System.out);
    }

    public void write(String data) {
        if (data == null) {
            return;
        }

        try {
            write(data.getBytes());
        } catch (IOException e) {
            logger.error("Error while writing data to standard output stream!", e);
        }
    }
}
