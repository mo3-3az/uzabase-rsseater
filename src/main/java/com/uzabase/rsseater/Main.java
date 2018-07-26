package com.uzabase.rsseater;

import com.uzabase.rsseater.rss.RssEater;

/**
 * @author Moath
 */
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        RssEater rssEater;
        if (args.length == 0) {
            rssEater = new RssEater();
        } else {
            rssEater = new RssEater(args[0]);
        }

        rssEater.process();

        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
        System.out.println("Memory taken: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000);
    }
}
