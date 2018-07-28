package com.uzabase.rsseater.config;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
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

    private static final String DEFAULT_CONFIG_FILE = "config.json";

    private static final String JSON_CONFIG_KEY_FEEDS_ENDPOINT = "feedsEndpoint";
    private static final String JSON_CONFIG_KEY_PROCESS_PHRASE = "processPhrase";
    private static final String JSON_CONFIG_KEY_CASE_SENSITIVE = "caseSensitive";
    private static final String JSON_CONFIG_KEY_FIELDS_TO_PROCESS = "fieldsToProcess";

    private JsonObject config;

    public JsonConfig() {
        this(null);
    }

    /**
     * Will load the passed file if passed and a valid json file, otherwise will terminate.
     */
    public JsonConfig(String configFile) {
        InputStream configFileStream;
        if (configFile == null) {
            configFileStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE);
            LOGGER.info("Loading default configurations.");
        } else {
            try {
                configFileStream = new FileInputStream(new File(configFile));
                LOGGER.info("Loading configurations file: " + configFile);
            } catch (FileNotFoundException e) {
                LOGGER.fatal("Error while loading configurations from file: " + configFile, e);
                throw new RuntimeException("Error while loading configurations from file: " + configFile, e);
            }
        }

        try {
            config = new JsonParser().parse(new InputStreamReader(configFileStream)).getAsJsonObject();
        } catch (JsonIOException | JsonSyntaxException e) {
            LOGGER.fatal("Error while parsing configurations from file: " + configFile + ", falling back to default configurations.", e);
            throw new RuntimeException("Error while parsing configurations from file: " + configFile, e);
        }
    }

    @Override
    public String getFeedsEndpoint() {
        return config.get(JSON_CONFIG_KEY_FEEDS_ENDPOINT).getAsString();
    }

    @Override
    public String getProcessPhrase() {
        return config.get(JSON_CONFIG_KEY_PROCESS_PHRASE).getAsString();
    }

    @Override
    public boolean caseSensitive() {
        return config.get(JSON_CONFIG_KEY_CASE_SENSITIVE).getAsBoolean();
    }

    @Override
    public List<String> getFieldsToProcess() {
        List<String> fieldsToProcess = new ArrayList<>();
        config.getAsJsonArray(JSON_CONFIG_KEY_FIELDS_TO_PROCESS).forEach(item -> fieldsToProcess.add(item.getAsString()));
        return fieldsToProcess;
    }
}
