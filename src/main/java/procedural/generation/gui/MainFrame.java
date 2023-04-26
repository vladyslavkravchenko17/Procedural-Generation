package main.java.procedural.generation.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(PixelArrayDisplay arrayDisplay, GUI gui) {
        super("Procedural Generation");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(arrayDisplay, BorderLayout.CENTER);
        panel.add(gui, BorderLayout.EAST);

        getContentPane().add(panel);

        setVisible(true);
    }
}
