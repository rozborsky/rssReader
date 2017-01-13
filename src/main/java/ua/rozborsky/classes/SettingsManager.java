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

    public List getURLs() {
        List urls = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile + "\\" + filename))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                urls.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        https://lenta.ru/rss
//        https://snob.ru/rss/all
//        http://www.pravda.com.ua/rss/

        return urls;
    }


    private void createFile() throws IOException {
        File file = new File(pathToFile + "\\" + filename);

        if (!file.exists() || !file.isFile()) {
            file.createNewFile();
        }
    }

    public void addChannel(String url) {
        try(FileWriter writer = new FileWriter(pathToFile + "\\" + filename, true))
        {
            createFile();

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.append(url);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(IOException ex){

        }
    }

    public void deleteChannel(String channelToRemove) {
        try {
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

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
