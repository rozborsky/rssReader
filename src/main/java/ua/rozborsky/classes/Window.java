package ua.rozborsky.classes;

import ua.rozborsky.interfaces.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by roman on 07.01.2017.
 */
public class Window implements View {

    final private short WIDTH = 400;
    private short HEIGHT = 300;
    private final Font FONT = new Font("Arial", Font.PLAIN, 20);

    public void create() {
        JFrame frame = new JFrame("RSS - news");
        setWindowParameters(frame, WIDTH, HEIGHT);
        setComponents(frame);

        frame.setResizable(false);
        frame.setVisible(true);
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
        frame.add(menu(frame));
        frame.add(contentPanel());
    }

    private JPanel menu(JFrame frame) {
        JPanel menu = new JPanel();
        menu.setLayout(new BorderLayout());

        menu.add(sizeMenu());

        menu.setSize((int)frame.getSize().getWidth(), 20);
        menu.setBackground(new Color(204,229,225));

        return menu;
    }

    private JPanel contentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(new Label("content"), BorderLayout.CENTER );
        content.setBackground(new Color(255,255,204));

        return content;
    }

    private JComboBox sizeMenu(){
        JComboBox size = new JComboBox();
        size.addItem("size");
        size.addItem("normal");
        size.addItem("big");

        size.setSelectedIndex(0);

        return size;
    }
}
