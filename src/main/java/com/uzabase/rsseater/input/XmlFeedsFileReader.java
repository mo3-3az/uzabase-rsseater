package com.uzabase.rsseater.input;

import java.io.InputStream;

/**
 * @author Moath
 */
public class XmlFeedsFileReader implements FeedsReader {


    public InputStream read(String feedsUri) {
        return getClass().getResourceAsStream("/feeds.xml");
    }
}
