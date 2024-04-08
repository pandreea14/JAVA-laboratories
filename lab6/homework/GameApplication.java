package org.example;

import javax.swing.*;
import java.awt.*;

public class GameApplication extends JFrame {

    DrawingPanel drawingPanel;
    ConfigPanel configPanel;
    ControlPanel controlPanel;

    public GameApplication() {
        setTitle("Game Application");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        drawingPanel = new DrawingPanel(this);

        // Add panels to the frame
        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameApplication::new);
    }
}
