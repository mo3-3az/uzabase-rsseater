package com.uzabase.rsseater.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a JSON-based configurator.
 *
 * @author Moath
 */
public class JsonConfig implements Config {

    private static final Logger LOGGER = Logger.getLogger(JsonConfig.class);

    private static final String CONFIG_JSON = "config.json";

    private static final String FEEDS_ENDPOINT = "feedsEndpoint";
    private static final String PROCESS_PHRASE = "processPhrase";
    private static final String CASE_SENSITIVE = "caseSensitive";
    private static final String FIELDS_TO_PROCESS = "fieldsToProcess";

    private JsonObject config;

    public JsonConfig(String configFile) {
        InputStream configFileStream;
        if (configFile == null) {
            configFileStream = getClass().getClassLoader().getResourceAsStream(CONFIG_JSON);
            LOGGER.info("Loading default configurations.");
        } else {
            try {
                configFileStream = new FileInputStream(new File(configFile));
                LOGGER.info("Loading file configurations: " + configFile);
            } catch (FileNotFoundException e) {
                LOGGER.error("Error while loading configurations from file: " + configFile + ", falling back to default configurations.", e);
                configFileStream = getClass().getClassLoader().getResourceAsStream(configFile);
            }
        }

        config = new JsonParser().parse(new InputStreamReader(configFileStream)).getAsJsonObject();
    }

    public String getFeedsEndpoint() {
        return config.get(FEEDS_ENDPOINT).getAsString();
    }

    @Override
    public String getProcessPhrase() {
        return config.get(PROCESS_PHRASE).getAsString();
    }

    @Override
    public boolean caseSensitive() {
        return config.get(CASE_SENSITIVE).getAsBoolean();
    }

    public List<String> getFieldsToProcess() {
        List<String> fieldsToProcess = new ArrayList<>();
        config.getAsJsonArray(FIELDS_TO_PROCESS).forEach(item -> fieldsToProcess.add(item.getAsString()));
        return fieldsToProcess;
    }
}
