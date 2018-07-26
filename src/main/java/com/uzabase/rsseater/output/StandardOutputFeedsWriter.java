package com.uzabase.rsseater.output;

import org.apache.log4j.Logger;

/**
 * @author Moath
 */
public class StandardOutputFeedsWriter implements FeedsWriter {

    private final static Logger logger = Logger.getLogger(StandardOutputFeedsWriter.class);

    public void write(String feeds) {
        logger.info("\n" + feeds);
    }
}
