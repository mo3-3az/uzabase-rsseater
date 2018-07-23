package com.uzabase.rsseater.feeds;

/**
 * @author Moath
 */
public class FeedItem {
    private String title;
    private String link;
    private String description;
    private GUID guid;
    private Enclosure enclosure;

    public FeedItem(String title, String link, String description, GUID guid, Enclosure enclosure) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.guid = guid;
        this.enclosure = enclosure;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public GUID getGuid() {
        return guid;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public class GUID {
        private boolean isPermalink;
        private String id;

        public GUID(boolean isPermalink, String id) {
            this.isPermalink = isPermalink;
            this.id = id;
        }

        public boolean isPermalink() {
            return isPermalink;
        }

        public String getId() {
            return id;
        }
    }

    public class Enclosure {
        private String url;
        private String type;
        private int length;

        public Enclosure(String url, String type, int length) {
            this.url = url;
            this.type = type;
            this.length = length;
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public int getLength() {
            return length;
        }
    }
}
