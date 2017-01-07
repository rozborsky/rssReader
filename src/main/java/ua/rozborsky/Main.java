package ua.rozborsky;

import ua.rozborsky.classes.Window;
import ua.rozborsky.interfaces.View;

/**
 * Created by roman on 07.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        View view = new Window();
        view.create();
    }
}
