package com.uzabase.rsseater.process;

import com.uzabase.rsseater.config.Config;

import java.io.InputStream;

/**
 * Feeds processor APISs.
 *
 * @author Moath
 */
public interface FeedsProcessor {

    /**
     * This is the main API, takes in an XML input stream of the feeds and a config
     * to return a processed XML string.
     */
    String process(InputStream feeds, Config config);
}
