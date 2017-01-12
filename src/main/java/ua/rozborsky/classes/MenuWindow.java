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

    public void createWindow(JFrame mainWindow) {
        JFrame frame = new JFrame("menu");
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
        addChannelsPanel.add(textField(), BorderLayout.WEST);
        addChannelsPanel.add(addButton(), BorderLayout.EAST);
        return addChannelsPanel;
    }

    private JTextField textField() {
        JTextField fieldAddChannel = new JTextField(26);
        return fieldAddChannel;
    }

    private JPanel channelsPanel() {
        JPanel channelsPanel = new JPanel();
        List<String> channels = listChannels();
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

    private List<String> listChannels() {
        List<String> channels = new ArrayList<>();//todo tmp list
        channels.add("pravda");
        channels.add("pravda");
        channels.add("pravda");
        channels.add("pravda");
        channels.add("pravda");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("snob");
        channels.add("lenta");
        return channels;
    }

    private void addChannels(JPanel channelsPanel, List channels) {
        Iterator entries = channels.iterator();
        channelsPanel.setLayout(new BoxLayout(channelsPanel, BoxLayout.Y_AXIS));

        while (entries.hasNext()) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JLabel label = new JLabel("<html>" + entries.next().toString() + "</html>");
            panel.setPreferredSize(new Dimension(290, 20));
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            panel.add(label, BorderLayout.WEST);
            panel.add(deleteButton(), BorderLayout.EAST);

            channelsPanel.add(panel);
        }
    }

    private JButton deleteButton() {
        JButton button = new JButton("delete");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("delete");
            }
        });

        return button;
    }

    private JButton addButton() {
        JButton button = new JButton("add");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("add");
            }
        });

        return button;
    }
}
