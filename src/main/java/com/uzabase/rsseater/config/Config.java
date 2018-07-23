package com.uzabase.rsseater.config;

import java.util.List;

/**
 * @author Moath
 */
public interface Config {

    String getFeedsEndpoint();

    String getProcessPhrase();

    List<String> getFieldsToProcess();

}
