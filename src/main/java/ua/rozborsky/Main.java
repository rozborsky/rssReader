package ua.rozborsky;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.FeedException;
import ua.rozborsky.classes.Window;
import ua.rozborsky.classes.XMLreader;
import ua.rozborsky.interfaces.View;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {
    public static void main(String[] args){
        View view = new Window();
        view.create();

        XMLreader xmLreader = new XMLreader();

        List entries = null;
        try {
            entries = xmLreader.listNews("http://www.pravda.com.ua/rss/view_news/");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
        Iterator itEntries = entries.iterator();

        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            System.out.println(entry.getDescription().getValue());
            System.out.println(entry.getLink() + "\n");
        }

    }
}
