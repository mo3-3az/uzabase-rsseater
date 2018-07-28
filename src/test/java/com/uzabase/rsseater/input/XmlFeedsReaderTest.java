package com.uzabase.rsseater.input;


import junit.framework.TestCase;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class XmlFeedsReaderTest extends TestCase {

    private FeedsReader feedsReader;

    public XmlFeedsReaderTest() {
        feedsReader = new XmlFeedsReader();
    }

    public void testValidUri() {
        final InputStream inputStream = feedsReader.read("https://www.google.com");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
            Assert.assertTrue(inputStreamReader.read() != -1);
        } catch (IOException e) {
            Assert.fail();
        }
    }

    public void testInvalidUri() {
        try (InputStream inputStream = feedsReader.read("https://wwwgooglecom")) {
            Assert.assertNull(inputStream);
        } catch (IOException e) {
            Assert.assertTrue(true);
        }
    }
}