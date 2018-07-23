package com.uzabase.rsseater;

import com.uzabase.rsseater.config.Config;
import com.uzabase.rsseater.config.JsonConfig;
import com.uzabase.rsseater.convert.FeedsConverter;
import com.uzabase.rsseater.convert.XmlFeedsConverter;
import com.uzabase.rsseater.feeds.Feeds;
import com.uzabase.rsseater.input.FeedsReader;
import com.uzabase.rsseater.input.XmlFeedsFileReader;
import com.uzabase.rsseater.output.FeedsWriter;
import com.uzabase.rsseater.output.FileOutputFeedsWriter;
import com.uzabase.rsseater.output.StandardOutputFeedsWriter;
import org.w3c.dom.Document;

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
        feedsConverter = new XmlFeedsConverter(config.getFieldsToProcess(), config.getProcessPhrase());
        fileOutputFeedsWriter = new FileOutputFeedsWriter();
        stdOutputFeedsWriter = new StandardOutputFeedsWriter();
    }

    public void process() {
        final Document feeds = feedsReader.read(config.getFeedsEndpoint());
        final Feeds convertedFeeds = feedsConverter.convert(feeds);
        fileOutputFeedsWriter.write(convertedFeeds);
        stdOutputFeedsWriter.write(convertedFeeds);
    }


    public static void main(String[] args) {
        RssEater rssEater;
        if (args.length == 0) {
            rssEater = new RssEater();
        } else {
            rssEater = new RssEater(args[0]);
        }

        rssEater.process();
    }
}
