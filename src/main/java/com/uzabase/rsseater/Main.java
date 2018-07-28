package com.uzabase.rsseater;

import com.uzabase.rsseater.rss.RssEater;

/**
 * This is the main entry point to the application.
 *
 * @author Moath
 */
public class Main {

    /**
     * You can optionally pass a config file.
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {

            RssEater rssEater;
            if (args.length == 0) {
                rssEater = new RssEater();
            } else {
                rssEater = new RssEater(args[0]);
            }

            rssEater.process();
        }

        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
//        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory in bytes: " + memory);
        System.out.println("Used memory in megabytes: " + bytesToMegabytes(memory));

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

}
