package ua.rozborsky;

import ua.rozborsky.classes.SettingsManager;
import ua.rozborsky.classes.Window;
import ua.rozborsky.classes.XMLreader;
import ua.rozborsky.interfaces.View;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(news.size());
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

        return allNews;
    }
}
