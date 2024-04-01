package org.example;

import javax.swing.*;
import java.awt.*;

public class GameApplication extends JFrame {
    private JPanel configPanel;
    private DrawingPanel canvasPanel; // Use custom panel for drawing
    private JPanel controlPanel;

    public GameApplication() {
        setTitle("Game Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components
        createConfigPanel();
        createCanvasPanel();
        createControlPanel();

        // Add panels to the frame
        add(configPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createConfigPanel() {
        configPanel = new JPanel();
        // Add components to the config panel (label, input fields, button)
        JLabel gridSizeLabel = new JLabel("Grid Size:");
        JTextField gridSizeField = new JTextField(5);
        JButton createButton = new JButton("Create Game");

        createButton.addActionListener(e -> {
            // When create button is clicked, redraw the canvas with new grid size
            canvasPanel.setGridSize(Integer.parseInt(gridSizeField.getText()));
            canvasPanel.repaint();
        });

        // Add components to the config panel
        configPanel.add(gridSizeLabel);
        configPanel.add(gridSizeField);
        configPanel.add(createButton);
    }

    private void createCanvasPanel() {
        canvasPanel = new DrawingPanel();
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        // Add control buttons (Load, Save, Exit, etc.)
        JButton loadButton = new JButton("Load");
        JButton saveButton = new JButton("Save");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the control panel
        controlPanel.add(loadButton);
        controlPanel.add(saveButton);
        controlPanel.add(exitButton);
    }

    // Custom panel for drawing
    private class DrawingPanel extends JPanel {
        private int gridSize = 50; // Default grid size

        // Method to set grid size
        public void setGridSize(int gridSize) {
            this.gridSize = gridSize;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();

            // Draw grid lines based on grid size
            g.setColor(Color.BLACK);
            for (int x = 0; x <= width; x += gridSize) {
                g.drawLine(x, 0, x, height);
            }
            for (int y = 0; y <= height; y += gridSize) {
                g.drawLine(0, y, width, y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameApplication::new);
    }
}
