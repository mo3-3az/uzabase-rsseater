package com.uzabase.rsseater.output;


import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileOutputFeedsWriterTest extends TestCase {

    private FeedsWriter feedsWriter;

    public FileOutputFeedsWriterTest() {
        feedsWriter = new FileOutputFeedsWriter();
    }

    @Test
    public void testValidData() {
        feedsWriter.write("Test");
        Assert.assertTrue(new File("output.xml").exists());
    }

    @Test
    public void testInvalidData() {
        String outputFileName = "output.xml";
        final boolean delete = new File(outputFileName).delete();
        if (!delete) {
            outputFileName = String.format("%s%d", outputFileName, System.currentTimeMillis());
        }

        feedsWriter.write(null);
        Assert.assertFalse(new File(outputFileName).exists());
    }
}