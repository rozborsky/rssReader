package ua.rozborsky.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 08.01.2017.
 */
public class SettingsManager {
    private String pathToFile = "C:\\Users\\roman";
    private String filename = "RSSreaderURLs.txt";

    public List getURLs() throws IOException {
        List <String> channels = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(pathToFile + "\\" + filename));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            channels.add(currentLine);
        }
        br.close();
//        https://lenta.ru/rss
//        https://snob.ru/rss/all
//        http://www.pravda.com.ua/rss/

        return channels;
    }


    private void createFile() throws IOException {
        File file = new File(pathToFile + "\\" + filename);
        if (!file.exists() || !file.isFile()) {
            file.createNewFile();
        }
    }

    public void addChannel(String url) throws IOException {
        FileWriter writer = new FileWriter(pathToFile + "\\" + filename, true);
        createFile();

        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.append(url);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void deleteChannel(String channelToRemove) throws IOException {
        File channel = new File(pathToFile + "\\" + filename);
        File file = new File(channel.getAbsolutePath() + ".tmp");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(channel));
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        String line ;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.trim().equals(channelToRemove)) {
                printWriter.println(line);
                printWriter.flush();
            }
        }
        printWriter.close();
        bufferedReader.close();
        channel.delete();
        file.renameTo(channel);
    }
}
