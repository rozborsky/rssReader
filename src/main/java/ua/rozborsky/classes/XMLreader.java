package ua.rozborsky.classes;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by roman on 07.01.2017.
 */
public class XMLreader {

    public List listNews(String addres){
        SyndFeed feed = null;
        try{
            URL url = new URL(addres);
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(httpcon));
        } catch (MalformedURLException e) {

        } catch (IOException e)  {

        } catch (FeedException  e) {

        }
        return feed.getEntries();
    }
}
