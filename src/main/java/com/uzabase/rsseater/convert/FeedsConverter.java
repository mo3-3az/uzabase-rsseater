package com.uzabase.rsseater.convert;

import com.uzabase.rsseater.feeds.Feeds;
import org.w3c.dom.Document;

import java.util.List;

public interface FeedsConverter {

    Feeds convert(Document feeds);
}
