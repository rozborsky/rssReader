package ua.rozborsky.classes;

import javax.swing.*;

/**
 * Created by roman on 14.01.2017.
 */
public class ErrorWindow {

    public ErrorWindow(JFrame mainWindow, String message) {
        JFrame frame = new JFrame("channels");
        setWindowParameters(mainWindow, frame);

        frame.add(new JLabel(message));
        frame.setVisible(true);
    }

    private void setWindowParameters(JFrame mainWindow, JFrame frame) {
        frame.setSize(mainWindow.getWidth() - 100, mainWindow.getHeight() - 200);
        frame.setLocationRelativeTo(mainWindow);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
    }
}
