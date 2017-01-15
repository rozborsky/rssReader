package ua.rozborsky;

import ua.rozborsky.classes.*;
import ua.rozborsky.interfaces.View;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {
    public static void main(String[] args){
        waitConnection();
        View view = new Window();
        view.createWindow();
    }

    private static void waitConnection() {
        TestConnection testConnection = new TestConnection();

        while (!testConnection.connected()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
