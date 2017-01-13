package ua.rozborsky.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by roman on 12.01.2017.
 */
public class MenuWindow{

    SettingsManager settingsManager = new SettingsManager();

    public void createWindow(JFrame mainWindow) {
        JFrame frame = new JFrame("channels");
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
        frame.add(addChannelPanel(), BorderLayout.NORTH);
        frame.add(scrollBar(channelsPanel()));
    }

    private JPanel addChannelPanel() {
        JPanel addChannelsPanel = new JPanel();
        addChannelsPanel.setLayout(new BorderLayout());
        JTextField textField = textField();
        addChannelsPanel.add(textField, BorderLayout.WEST);
        addChannelsPanel.add(addButton(textField), BorderLayout.EAST);
        return addChannelsPanel;
    }

    private JTextField textField() {
        JTextField fieldAddChannel = new JTextField(26);
        return fieldAddChannel;
    }

    private JPanel channelsPanel() {
        JPanel channelsPanel = new JPanel();
        List<String> channels = settingsManager.getURLs();
        addChannels(channelsPanel, channels);

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

    private void addChannels(JPanel channelsPanel, List channels) {
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
            public void actionPerformed(ActionEvent e)
            {
                SettingsManager settingsManager = new SettingsManager();
                settingsManager.deleteChannel(channel);
            }
        });

        return button;
    }

    private JButton addButton(final JTextField textField) {
        JButton button = new JButton("add");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                SettingsManager settingsManager = new SettingsManager();
                settingsManager.addChannel(textField.getText());
                textField.setText("");
            }
        });

        return button;
    }
}
