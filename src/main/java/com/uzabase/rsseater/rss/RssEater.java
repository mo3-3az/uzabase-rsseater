package com.uzabase.rsseater.rss;

import com.uzabase.rsseater.config.Config;
import com.uzabase.rsseater.config.JsonConfig;
import com.uzabase.rsseater.convert.FeedsConverter;
import com.uzabase.rsseater.convert.XmlFeedsConverter;
import com.uzabase.rsseater.input.FeedsReader;
import com.uzabase.rsseater.input.XmlFeedsFileReader;
import com.uzabase.rsseater.input.XmlFeedsReader;
import com.uzabase.rsseater.output.FeedsWriter;
import com.uzabase.rsseater.output.FileOutputFeedsWriter;
import com.uzabase.rsseater.output.StandardOutputFeedsWriter;

import java.io.InputStream;

/**
 * <ul>
 * <li>Load configurations.</li>
 * <li>Consume feeds.</li>
 * <li>Convert feeds.</li>
 * <li>Print out results.</li>
 * </ul>
 *
 * @author Moath
 */
public class RssEater {

    private Config config;
    private FeedsReader feedsReader;
    private FeedsConverter feedsConverter;
    private FeedsWriter stdOutputFeedsWriter;
    private FeedsWriter fileOutputFeedsWriter;

    public RssEater() {
        this(null);
    }

    public RssEater(String configFile) {
        config = new JsonConfig(configFile);
        feedsReader = new XmlFeedsFileReader();
        feedsConverter = new XmlFeedsConverter(config);
        fileOutputFeedsWriter = new FileOutputFeedsWriter();
        stdOutputFeedsWriter = new StandardOutputFeedsWriter();
    }

    public void process() {
        final InputStream feeds = feedsReader.read(config.getFeedsEndpoint());
        final String convertedFeeds = feedsConverter.convert(feeds);
        fileOutputFeedsWriter.write(convertedFeeds);
        stdOutputFeedsWriter.write(convertedFeeds);
    }

}
