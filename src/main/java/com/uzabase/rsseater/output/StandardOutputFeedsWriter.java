package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

/**
 * Output stream based implementation which will write the processed feeds to standard output stream.
 *
 * @author Moath
 */
public class StandardOutputFeedsWriter implements FeedsWriter {

    private final static Logger logger = Logger.getLogger(StandardOutputFeedsWriter.class);

    public void write(String feeds) {
        if (feeds != null) {
            System.out.println(feeds);
            logger.info("Processed feeds were written to standard output stream successfully.");
        }
    }
}
