package com.uzabase.rsseater.output;

import junit.framework.TestCase;
import org.junit.Test;

public class StandardOutputFeedsWriterTest extends TestCase {

    public StandardOutputFeedsWriterTest() {
    }

    @Test
    public void test() {
        FeedsWriter feedsWriter = new StandardOutputFeedsWriter();
        feedsWriter.write("Test");
    }
}