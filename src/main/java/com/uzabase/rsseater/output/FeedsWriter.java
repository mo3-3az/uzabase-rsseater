package com.uzabase.rsseater.output;

import com.uzabase.rsseater.feeds.Feeds;

public interface FeedsWriter {

    void write(Feeds feeds);
}
