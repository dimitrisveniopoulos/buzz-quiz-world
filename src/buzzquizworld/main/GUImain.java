package buzzquizworld.main;

import javax.swing.*;
import java.io.IOException;

public class GUImain {
    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUIGameHandler game = new GUIGameHandler();
    }
}
