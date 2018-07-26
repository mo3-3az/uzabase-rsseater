package com.uzabase.rsseater.input;

import java.io.InputStream;

/**
 * Feeds reader APIs.
 */
public interface FeedsReader {

    /**
     * This is the main API, it takes in a URI and return a stream of that URI.
     */
    InputStream read(String feedsUri);
}
