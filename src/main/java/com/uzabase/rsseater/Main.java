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
        RssEater rssEater;
        if (args.length == 0) {
            rssEater = new RssEater();
        } else {
            rssEater = new RssEater(args[0]);
        }

        rssEater.process();
    }
}
