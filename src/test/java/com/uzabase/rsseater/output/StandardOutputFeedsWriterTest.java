package com.uzabase.rsseater.output;

import junit.framework.TestCase;

public class StandardOutputFeedsWriterTest extends TestCase {

    public StandardOutputFeedsWriterTest() {
    }

    public void test() {
        FeedsWriter feedsWriter = new StandardOutputFeedsWriter();
        feedsWriter.write("Test");
    }
}