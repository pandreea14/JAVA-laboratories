package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final GameApplication frame;
    JLabel label;
    JComboBox<Integer> rowsComboBox;
    JComboBox<Integer> colsComboBox;

    public ConfigPanel(GameApplication frame) {
        this.frame = frame;
        init();
        //repaint();
    }

    private void init() {
        //create the label
        label = new JLabel("Grid size ");

        // Initialize JComboBox for rows and columns
        rowsComboBox = new JComboBox<>(createRangeArray(5, 50)); // Default range from 2 to 100
        colsComboBox = new JComboBox<>(createRangeArray(5, 50));

        JButton createButton = new JButton("Create Game");

        // Add action listener to the create button
        createButton.addActionListener(e -> {
            //repaint();
            int rows = frame.configPanel.getRows();
            int cols = frame.configPanel.getCols();
            frame.drawingPanel.init(rows, cols);
            frame.drawingPanel.paintComponent(getGraphics());
            frame.drawingPanel.paintSticks((Graphics2D) frame.drawingPanel.getGraphics());
        });

        // Add components to the panel
        add(label);
        add(new JLabel("Rows:"));
        add(rowsComboBox);
        add(new JLabel("Cols:"));
        add(colsComboBox);
        add(createButton);
    }
    private Integer[] createRangeArray(int start, int end) {
        Integer[] array = new Integer[end - start + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = start++;
        }
        return array;
    }
    public int getRows() throws NumberFormatException {
        //repaint();
        Integer selectedRows = (Integer) rowsComboBox.getSelectedItem();
        if (selectedRows == null) {
            throw new NumberFormatException("Rows field is empty");
        }
        return selectedRows;
    }

    public int getCols() throws NumberFormatException {
        //repaint();
        Integer selectedCols = (Integer) colsComboBox.getSelectedItem();
        if (selectedCols == null) {
            throw new NumberFormatException("Cols field is empty");
        }
        return selectedCols;
    }
}