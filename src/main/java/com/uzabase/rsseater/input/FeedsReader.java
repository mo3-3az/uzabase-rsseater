package com.uzabase.rsseater.input;

import org.w3c.dom.Document;

import java.io.InputStream;

public interface FeedsReader {

    InputStream read(String feedsUrl);
}
