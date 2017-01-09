package ua.rozborsky;

import com.sun.syndication.feed.synd.SyndEntry;
import ua.rozborsky.classes.News;
import ua.rozborsky.classes.SettingsManager;
import ua.rozborsky.classes.Window;
import ua.rozborsky.classes.XMLreader;
import ua.rozborsky.interfaces.View;

import java.util.*;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {

    public static void main(String[] args){
        String pathToFile = "C:\\Users\\roman";
        String filename = "RSSreaderURLs.txt";

        SettingsManager settingsManager = new SettingsManager(pathToFile, filename);
        List urls = settingsManager.getURLs();
        List news = createListNews(urls);

        View view = new Window();
        view.addContent(news);
        view.createWindow();
    }

    private static List createListNews(List urls) {
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

    private static List sortNews(List listNews) {
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
