package ua.rozborsky.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by roman on 12.01.2017.
 */
public class MenuWindow{
    private JFrame mainWindow;
    private SettingsManager settingsManager = new SettingsManager();

    public void createWindow(JFrame mainWindow) {
        JFrame frame = new JFrame("channels");
        this.mainWindow = mainWindow;
        setWindowParameters(mainWindow, frame);
        setComponents(frame);

        frame.setVisible(true);
    }

    private void setWindowParameters(JFrame mainWindow, JFrame frame) {
        frame.setSize(mainWindow.getWidth() - 40, mainWindow.getHeight() - 100);
        frame.setLocationRelativeTo(mainWindow);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
    }

    private void setComponents(JFrame frame) {
        frame.setLayout(new BorderLayout());
        JPanel channelsPanel = channelsPanel();
        frame.add(inputChannelPanel(channelsPanel), BorderLayout.NORTH);
        frame.add(scrollBar(channelsPanel));
    }

    private JPanel inputChannelPanel(JPanel channelsPanel) {
        JPanel addChannelsPanel = new JPanel();
        addChannelsPanel.setLayout(new BorderLayout());
        JTextField textField = textField();
        addChannelsPanel.add(textField, BorderLayout.WEST);
        addChannelsPanel.add(addButton(textField, channelsPanel), BorderLayout.EAST);

        return addChannelsPanel;
    }

    private JTextField textField() {
        JTextField fieldAddChannel = new JTextField(26);

        return fieldAddChannel;
    }

    private JPanel channelsPanel() {
        JPanel channelsPanel = new JPanel();
        List<String> channels = Collections.EMPTY_LIST;
        try {
            channels = settingsManager.getURLs();
        } catch (IOException e) {
            new ErrorWindow(mainWindow, "can't get channels list from property file");
        }
        channels(channelsPanel, channels);
        channelsPanel.setBackground(new Color(255, 255, 204));

        return channelsPanel;
    }

    private JScrollPane scrollBar(JPanel contentPanel) {
        JScrollPane jScrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        return jScrollPane;
    }

    private void channels(JPanel channelsPanel, List channels) {
        Iterator entries = channels.iterator();
        channelsPanel.setLayout(new BoxLayout(channelsPanel, BoxLayout.Y_AXIS));

        while (entries.hasNext()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            String channel = entries.next().toString();
            JLabel label = new JLabel("<html>" + channel + "</html>");
            panel.setPreferredSize(new Dimension(290, 20));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            panel.add(label, BorderLayout.WEST);
            panel.add(deleteButton(channel), BorderLayout.EAST);
            channelsPanel.add(panel);
        }
    }

    private JButton deleteButton(final String channel) {
        JButton button = new JButton("delete");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    settingsManager.deleteChannel(channel);
                } catch (IOException e1) {
                    new ErrorWindow(mainWindow, "can't delete channel");
                }
            }
        });

        return button;
    }

    private JButton addButton(final JTextField textField, final JPanel channelsPanel) {
        JButton button = new JButton("add");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    settingsManager.addChannel(textField.getText());
                } catch (IOException e1) {
                    new ErrorWindow(mainWindow, "can't add channel to property file");
                }
                textField.setText("");
            SwingUtilities.updateComponentTreeUI(channelsPanel);
            }
        });

        return button;
    }
}
