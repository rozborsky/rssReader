package ua.rozborsky.classes;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.*;

/**
 * Created by roman on 10.01.2017.
 */
public class NewsManager {

    public List listNews(List urls) {
        List allNews = new ArrayList();
        XMLreader xmLreader = new XMLreader();

        for (int i = 0; i < urls.size(); i++) {
            List news = xmLreader.listNews(urls.get(i).toString());
            news = news.subList(0, 20);
            if (news != null) {
                allNews.addAll(news);
            }
        }
        allNews = sortNews(allNews);

        return allNews;
    }

    private List sortNews(List listNews) {
        List news = new ArrayList();
        Iterator itEntries = listNews.iterator();
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            news.add(new News(entry.getTitle(), entry.getLink(), entry.getPublishedDate().getTime()));
        }
        Collections.sort(news, snorderer);

        return news;
    }

    static Comparator<News> snorderer = new Comparator<News>() {
        public int compare(News o1, News o2) {
            return Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime()));
        }
    };
}
