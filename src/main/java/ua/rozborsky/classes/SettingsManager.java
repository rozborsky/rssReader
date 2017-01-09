package ua.rozborsky.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 08.01.2017.
 */
public class SettingsManager {

    private String pathToFile;
    private String filename;

    public SettingsManager(String pathToFile, String filename) {
        this.pathToFile = pathToFile;
        this.filename = filename;
    }

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


    private void createFile(String pathToFile, String filename) throws IOException {
        File file = new File(pathToFile + "\\" + filename);

        if (!file.exists() || !file.isFile()) {
            file.createNewFile();
        }
    }
}
