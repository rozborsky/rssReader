package ua.rozborsky.classes;

/**
 * Created by roman on 09.01.2017.
 */
public class News {
    private String title;
    private String url;
    private long time;

    public News(String title, String url, long time) {
        this.title = title;
        this.url = url;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public long getTime() {
        return time;
    }
}
