package main.java.procedural.generation.gui;

import main.java.procedural.generation.service.GenerationService;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class GUI extends JPanel implements ActionListener {

    private final JFormattedTextField field1;
    private final JFormattedTextField field2;
    private final JFormattedTextField field3;
    private final JFormattedTextField field4;
    private final JComboBox comboBox;
    private final JButton button;
    private final PixelArrayDisplay display;

    public GUI(PixelArrayDisplay display) {
        this.display = display;

        setSize(600, 200);

        JLabel label1 = new JLabel("Map size:");
        JLabel label2 = new JLabel("Seed:");
        JLabel label3 = new JLabel("*Land&Ocean* amount of generations:");
        JLabel label4 = new JLabel("*Sand* amount of generations:");
        JLabel label5 = new JLabel("*Shallow water* amount of generations:");


        NumberFormat format = NumberFormat.getInstance();
        DecimalFormat decimalFormat = (DecimalFormat) format;
        decimalFormat.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(decimalFormat);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setMinimum(0);

        field1 = new JFormattedTextField(formatter);
        field1.setValue(new Random().nextLong());
        field2 = new JFormattedTextField(formatter);
        field2.setValue(200);
        field3 = new JFormattedTextField(formatter);
        field3.setValue(100);
        field4 = new JFormattedTextField(formatter);
        field4.setValue(10);

        String[] items = {"100x100", "200x200", "500x500", "1000x1000"};
        comboBox = new JComboBox<>(items);
        comboBox.setBackground(Color.WHITE);

        button = new JButton("Generate!");
        button.addActionListener(this);



        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(label1);
        panel.add(comboBox);
        panel.add(label2);
        panel.add(field1);
        panel.add(label3);
        panel.add(field2);
        panel.add(label4);
        panel.add(field3);
        panel.add(label5);
        panel.add(field4);
        panel.add(button);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            long seed = Long.parseLong(field1.getText());
            int amountOfLandOceanGenerations = Integer.parseInt(field2.getText());
            int amountOfSandGenerations = Integer.parseInt(field3.getText());
            int amountOfShallowWaterGenerations = Integer.parseInt(field4.getText());
            String selectedSize = (String) comboBox.getSelectedItem();
            assert selectedSize != null;
            int size = Integer.parseInt(selectedSize.substring(0, selectedSize.indexOf("x")));
            startMethod(seed, size, amountOfLandOceanGenerations, amountOfSandGenerations, amountOfShallowWaterGenerations);
        }
    }

    private void startMethod(long seed, int size, int amountOfLandOceanGenerations, int amountOfSandGenerations,
                             int amountOfShallowWaterGenerations) {
        int pixelSize = 1000/size;
        Thread thread = new Thread(() -> GenerationService.generateMap(seed,size, pixelSize, display, amountOfLandOceanGenerations,
                amountOfSandGenerations, amountOfShallowWaterGenerations));
        thread.start();

    }
}
