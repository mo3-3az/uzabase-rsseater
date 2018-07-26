package com.uzabase.rsseater.config;

import java.util.List;

/**
 * This is the configurations that are needed to process the XML feeds.
 *
 * @author Moath
 */
public interface Config {

    /**
     * The endpoint to which the feeds will be consumer from.
     */
    String getFeedsEndpoint();

    /**
     * The phrase that will be processed (replaced).
     */
    String getProcessPhrase();

    /**
     * Process with case sensitive or not.
     */
    boolean caseSensitive();

    /**
     * Which fields (tags) should be processed.
     */
    List<String> getFieldsToProcess();

}
