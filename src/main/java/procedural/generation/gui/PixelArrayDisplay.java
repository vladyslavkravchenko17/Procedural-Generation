package main.java.procedural.generation.gui;

import main.java.procedural.generation.service.MapGenerator;

import javax.swing.*;
import java.awt.*;

public class PixelArrayDisplay extends JPanel {
    private int[][] pixelArray;
    private int pixelSize;

    public PixelArrayDisplay(int[][] pixelArray, int pixelSize) {
        this.pixelArray = pixelArray;
        this.pixelSize = pixelSize;
        init();
    }

    private void init() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.getContentPane().add(this);
        frame.setVisible(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < pixelArray.length; x++) {
            for (int y = 0; y < pixelArray[0].length; y++) {
                Color color = getColorBySurfaceNum(pixelArray[x][y]);
                g.setColor(color);
                g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
            }
        }
    }

    public void setPixelArray(int[][] pixelArray) {
        this.pixelArray = pixelArray;
        repaint();
    }

    private Color getColorBySurfaceNum(int num) {
        if (num == MapGenerator.OCEAN) return new Color(0, 130, 217);
        if (num == MapGenerator.LAND) return new Color(0, 173, 12);
        if (num == MapGenerator.SAND) return new Color(219, 214, 66);
        if (num == MapGenerator.SHALLOW_WATER) return new Color(93, 184, 245);
        return Color.BLACK;
    }

    public void setPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
    }
}