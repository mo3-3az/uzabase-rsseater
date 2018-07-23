package com.uzabase.rsseater.input;

import org.w3c.dom.Document;

public interface FeedsReader {

    Document read(String feedsUrl);
}
