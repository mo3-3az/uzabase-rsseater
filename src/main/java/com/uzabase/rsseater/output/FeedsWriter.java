package com.uzabase.rsseater.output;

/**
 * Feeds writer APIs.
 */
public interface FeedsWriter {

    /**
     * Thi method will take a string of XML feeds and write it somewhere.
     */
    void write(String feeds);
}
