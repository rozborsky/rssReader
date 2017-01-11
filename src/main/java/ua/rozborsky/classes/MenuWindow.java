package ua.rozborsky.classes;

import javax.swing.*;

/**
 * Created by roman on 12.01.2017.
 */
public class MenuWindow {

    private JComboBox comboBox() {
        JComboBox comboBox = new JComboBox();
        comboBox.setSize(50, 40);

        comboBox.addItem("channels");
//        for (int i = 0; i < channels.size(); i++) {
//            comboBox.addItem(channels.toArray()[i]);
//        }

        return comboBox;
    }


    //JTextField textField=new JTextField(20);//todo add channel

    //menu.add(textField);
}
