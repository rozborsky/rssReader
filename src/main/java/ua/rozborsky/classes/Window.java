package ua.rozborsky.classes;

import ua.rozborsky.interfaces.View;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roman on 07.01.2017.
 */
public class Window implements View {

    private short WIDTH = 400;
    private short HEIGHT = 300;
    private final Font FONT = new Font("Arial", Font.PLAIN, 15);
    private List content;

    public void createWindow() {
        JFrame frame = new JFrame("RSS reader");
        setWindowParameters(frame, WIDTH, HEIGHT);
        setComponents(frame);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void addContent(List content) {
        this.content = content;
    }

    private void setWindowParameters(JFrame frame, short width, short height) {
        frame.setSize(width, height);
        setWindowPosition(frame, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setWindowPosition(JFrame frame, short width, short height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        short screenWidth = (short)screenSize.width;
        short screenHeigth = (short)screenSize.height;

        frame.setLocation(screenWidth - width, screenHeigth - height - taskBarHeight(screenHeigth));
    }

    private short taskBarHeight(short screenHeigth) {
        Rectangle windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        return  (short)(screenHeigth - windowSize.height);
    }

    private void setComponents(JFrame frame) {
        frame.setLayout(new BorderLayout());
        frame.add(menu(frame), BorderLayout.NORTH);

        JPanel contentPanel = contentPanel(content);
        frame.add(scrollBar(contentPanel), BorderLayout.CENTER);
    }

    private JScrollPane scrollBar(JPanel contentPanel) {
        return new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private JPanel menu(JFrame frame) {//todo flowlayout
        JPanel menu = new JPanel();

        menu.setSize((int)frame.getSize().getWidth(), 20);
        menu.setBackground(new Color(204,229,225));

        return menu;
    }

    private JPanel contentPanel(List content) {
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
                        } catch (IOException ex) {
                            // do nothing
                        } catch (URISyntaxException ex) {
                            //do nothing
                        }
                    }
                }
            }
        });

        return link;
    }
}
