package ua.rozborsky;

import ua.rozborsky.classes.SettingsManager;
import ua.rozborsky.classes.Window;
import ua.rozborsky.classes.XMLreader;
import ua.rozborsky.interfaces.View;

import java.io.File;
import java.io.IOException;
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

        View view = new Window();
        view.addContent(news);
        view.createWindow();

    }

    private static List createListNews(List urls) {
        List news = new ArrayList();
        XMLreader xmLreader = new XMLreader();

        for (int i = 0; i < urls.size(); i++) {
            if (xmLreader.listNews(urls.get(i).toString()) != null) {
                news.addAll(xmLreader.listNews(urls.get(i).toString()));
            }
        }

        return news;
    }


}
