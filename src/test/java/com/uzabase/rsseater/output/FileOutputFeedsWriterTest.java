package com.uzabase.rsseater.output;


import junit.framework.TestCase;
import org.junit.Assert;

import java.io.File;

public class FileOutputFeedsWriterTest extends TestCase {

    private FeedsWriter feedsWriter;

    public FileOutputFeedsWriterTest() {
        feedsWriter = new FileOutputFeedsWriter();
    }

    public void testValidData() {
        feedsWriter.write("Test");
        Assert.assertTrue(new File(FileOutputFeedsWriter.OUTPUT_XML).exists());
    }

    public void testInvalidData() {
        String outputFileName = FileOutputFeedsWriter.OUTPUT_XML;
        final boolean delete = new File(outputFileName).delete();
        if (!delete) {
            outputFileName = String.format("%s%d", outputFileName, System.currentTimeMillis());
        }

        feedsWriter.write(null);
        Assert.assertFalse(new File(outputFileName).exists());
    }
}