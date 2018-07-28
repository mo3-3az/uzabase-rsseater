package com.uzabase.rsseater.process;

import com.uzabase.rsseater.config.JsonConfig;
import junit.framework.TestCase;
import org.junit.Assert;

public class XmlFeedsProcessorTest extends TestCase {

    private FeedsProcessor feedsProcessor;

    public XmlFeedsProcessorTest() {
        feedsProcessor = new XmlFeedsProcessor();
    }

    public void testValidInputStream() {
        final JsonConfig config = new JsonConfig();
        final String processedFeeds = feedsProcessor.process(getClass().getResourceAsStream("/feeds.xml"), config);
        Assert.assertFalse(processedFeeds.contains(config.getProcessPhrase()));
    }

    public void testInvalidInputStream() {
        final JsonConfig config = new JsonConfig();
        final String processedFeeds = feedsProcessor.process(null, config);
        Assert.assertNull(processedFeeds);
    }
}