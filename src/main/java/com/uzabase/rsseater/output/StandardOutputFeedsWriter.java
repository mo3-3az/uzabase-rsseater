package com.uzabase.rsseater.output;

import com.uzabase.rsseater.feeds.Feeds;
import org.apache.log4j.Logger;

/**
 * @author Moath
 */
public class StandardOutputFeedsWriter implements FeedsWriter {

    private final static Logger logger = Logger.getLogger(StandardOutputFeedsWriter.class);

    public void write(Feeds feeds) {
        logger.info(feeds);
    }
}
