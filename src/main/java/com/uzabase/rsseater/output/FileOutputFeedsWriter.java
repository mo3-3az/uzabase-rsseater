package com.uzabase.rsseater.output;

import com.uzabase.rsseater.feeds.Feeds;
import org.apache.log4j.Logger;

/**
 * @author Moath
 */
public class FileOutputFeedsWriter implements FeedsWriter {

    private final static Logger logger = Logger.getLogger(FileOutputFeedsWriter.class);

    public void write(Feeds feeds) {
        logger.info("");
    }
}
