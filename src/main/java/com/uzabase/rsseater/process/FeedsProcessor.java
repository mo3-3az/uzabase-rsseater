package com.uzabase.rsseater.process;

import com.uzabase.rsseater.config.Config;

import java.io.InputStream;

/**
 * Feeds processor APIs.
 *
 * @author Moath
 */
public interface FeedsProcessor {

    /**
     * This is the main API, takes in an XML input stream of the feeds and a config
     * to process the XML.
     */
    void process(InputStream feeds, Config config);
}
