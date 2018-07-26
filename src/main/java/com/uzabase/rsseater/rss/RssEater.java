package com.uzabase.rsseater.rss;

import com.uzabase.rsseater.config.Config;
import com.uzabase.rsseater.config.JsonConfig;
import com.uzabase.rsseater.input.FeedsReader;
import com.uzabase.rsseater.input.XmlFeedsReader;
import com.uzabase.rsseater.output.FeedsWriter;
import com.uzabase.rsseater.output.FileOutputFeedsWriter;
import com.uzabase.rsseater.output.StandardOutputFeedsWriter;
import com.uzabase.rsseater.process.FeedsProcessor;
import com.uzabase.rsseater.process.XmlFeedsProcessor;

import java.io.InputStream;

/**
 * <ul>
 * <li>Load configurations.</li>
 * <li>Consume feeds.</li>
 * <li>Process feeds.</li>
 * <li>Print out results.</li>
 * </ul>
 *
 * @author Moath
 */
public class RssEater {

    private Config config;
    private FeedsReader feedsReader;
    private FeedsProcessor feedsProcessor;
    private FeedsWriter stdOutputFeedsWriter;
    private FeedsWriter fileOutputFeedsWriter;

    public RssEater() {
        this(null);
    }

    public RssEater(String configFile) {
        config = new JsonConfig(configFile);
        feedsReader = new XmlFeedsReader();
        feedsProcessor = new XmlFeedsProcessor();
        fileOutputFeedsWriter = new FileOutputFeedsWriter();
        stdOutputFeedsWriter = new StandardOutputFeedsWriter();
    }

    public void process() {
        final InputStream feeds = feedsReader.read(config.getFeedsEndpoint());
        final String processedFeeds = feedsProcessor.process(feeds, config);
        fileOutputFeedsWriter.write(processedFeeds);
        stdOutputFeedsWriter.write(processedFeeds);
    }

}
