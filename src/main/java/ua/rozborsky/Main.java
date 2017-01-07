package ua.rozborsky;

import ua.rozborsky.classes.Window;
import ua.rozborsky.classes.XMLreader;
import ua.rozborsky.interfaces.View;

import java.util.List;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {
    public static void main(String[] args){
        XMLreader xmLreader = new XMLreader();
        List content = xmLreader.listNews("http://www.pravda.com.ua/rss/view_news/");

        View view = new Window();
        view.addContent(content);
        view.createWindow();
    }
}
