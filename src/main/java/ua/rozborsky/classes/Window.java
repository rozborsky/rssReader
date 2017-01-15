package ua.rozborsky.classes;

import ua.rozborsky.interfaces.View;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roman on 07.01.2017.
 */
public class Window implements View {
    private short WIDTH = 400;
    private short HEIGHT = 300;
    private final Font FONT = new Font("Arial", Font.PLAIN, 15);
    private JFrame frame;

    public void createWindow() {
        frame = new JFrame("RSS reader");
        setWindowParameters(frame, WIDTH, HEIGHT);
        setComponents(frame);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private List news() {
        SettingsManager settingsManager = new SettingsManager();
        List urls = Collections.EMPTY_LIST;
        try {
            urls = settingsManager.getURLs();
        } catch (IOException e) {
            new ErrorWindow(frame, "can't find property file");
        }
        NewsManager newsManager = new NewsManager();

        return newsManager.listNews(urls);
    }

    private void setWindowParameters(JFrame frame, short width, short height) {
        frame.setSize(width, height);
        setWindowPosition(frame, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
    }

    private void setWindowPosition(JFrame frame, short width, short height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        short screenWidth = (short)screenSize.width;
        short screenHeigth = (short)screenSize.height;

        frame.setLocation(screenWidth - width, screenHeigth - height - taskBarHeight(screenHeigth));
    }

    private short taskBarHeight(short screenHeight) {
        Rectangle windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        return  (short)(screenHeight - windowSize.height);
    }

    private JScrollPane scrollBar(JPanel contentPanel) {
        JScrollPane jScrollPane = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        return jScrollPane;
    }

    private void setComponents(JFrame frame) {
        frame.setLayout(new BorderLayout());
        JPanel newsPanel = newsPanel(news());
        frame.add(menuPanel(newsPanel), BorderLayout.NORTH);
        frame.add(scrollBar(newsPanel), BorderLayout.CENTER);
    }

    private JPanel menuPanel(JPanel newsPanel) {
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new BorderLayout());
        menuBar.setSize((int) frame.getSize().getWidth(), 50);
        menuBar.setBackground(new Color(204, 229, 225));
        menuBar.add(menuButton(), BorderLayout.WEST);
        menuBar.add(getNewsButton(newsPanel), BorderLayout.EAST);

        return menuBar;
    }

    private JButton menuButton() {
        JButton button = new JButton("channels");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                MenuWindow window = new MenuWindow();
                window.createWindow(frame);
            }
        });

        return button;
    }

    private JButton getNewsButton(final JPanel newsPanel) {
        JButton button = new JButton("getNews");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                //newsPanel.revalidate();
                SwingUtilities.updateComponentTreeUI(newsPanel);
                System.out.println("getnews");
            }
        });

        return button;
    }

    private JPanel newsPanel(List content) {
        JPanel contentPanel = new JPanel();
        addNews(contentPanel, content);
        contentPanel.setBackground(new Color(255, 255, 204));

        return contentPanel;
    }

    private void addNews(JPanel contentPanel, List content) {
        Iterator entries = content.iterator();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        while (entries.hasNext()) {
            News  entry = (News) entries.next();
            JLabel label = new JLabel("<html>" + entry.getTitle() + "</html>");
            addLink(label, entry.getUrl());

            label.setPreferredSize(new Dimension(300, 30));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setFont(FONT);
            contentPanel.add(label);
        }
    }

    private JLabel addLink(JLabel link, final String url) {
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() > 0) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        URI uri = new URI(url);
                        desktop.browse(uri);
                    } catch (IOException | URISyntaxException ex) {
                        new ErrorWindow(frame, "can't open news");
                    }
                }
            }
            }
        });

        return link;
    }
}
