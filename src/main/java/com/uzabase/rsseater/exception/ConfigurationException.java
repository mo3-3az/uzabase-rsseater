package com.uzabase.rsseater.exception;

/**
 * @author Moath
 */
public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String message, Exception exception) {
        super(message, exception);
    }
}
