package main.java.procedural.generation;


import main.java.procedural.generation.gui.GUI;
import main.java.procedural.generation.gui.MainFrame;
import main.java.procedural.generation.gui.PixelArrayDisplay;

public class Main {

    public static void main(String[] args) {
        PixelArrayDisplay display = new PixelArrayDisplay(new int[100][100], 10);
        GUI gui = new GUI(display);

        MainFrame mainFrame = new MainFrame(display, gui);

    }
}
