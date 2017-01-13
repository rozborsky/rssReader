package ua.rozborsky;

import ua.rozborsky.classes.*;
import ua.rozborsky.interfaces.View;

import java.util.*;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {
    public static void main(String[] args){

        SettingsManager settingsManager = new SettingsManager();

        List urls = settingsManager.getURLs();

        NewsManager newsManager = new NewsManager();
        List news = newsManager.listNews(urls);

        View view = new Window();
        view.addContent(news);
        view.createWindow();
    }
}
