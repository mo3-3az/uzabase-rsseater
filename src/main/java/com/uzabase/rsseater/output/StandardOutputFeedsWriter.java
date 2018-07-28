package com.uzabase.rsseater.output;

/**
 * Output stream based implementation which will write the processed feeds to standard output stream.
 *
 * @author Moath
 */
public class StandardOutputFeedsWriter implements FeedsWriter {

    public void write(String feeds) {
        if (feeds != null) {
            System.out.println(feeds);
        }
    }
}
