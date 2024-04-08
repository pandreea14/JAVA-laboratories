package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final GameApplication frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    public ControlPanel(GameApplication frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));

        //add all buttons
        add(exitBtn);
        add(loadBtn);
        add(saveBtn);

        //configure listeners for all buttons
        exitBtn.addActionListener(this::setExitBtn);
        loadBtn.addActionListener(this::setLoadBtn);
        saveBtn.addActionListener(this::setSaveBtn);
    }

    private void setExitBtn(ActionEvent e) {
        frame.dispose();
    }

    private void setLoadBtn(ActionEvent e) {
        String filePath = "board.png";

        try {
            File file = new File(filePath); //citeste fisierul pentru imagine
            BufferedImage loadedImage = ImageIO.read(file);

            // da update la drawingPanel pentru a afisa continutul din imagine
            Graphics2D g2d = (Graphics2D) frame.drawingPanel.getGraphics();
            g2d.drawImage(loadedImage, 0, 0, frame.drawingPanel);

            // mesaj pentru jucator
            JOptionPane.showMessageDialog(frame, "Image loaded successfully from " + filePath, "Load Successful", JOptionPane.INFORMATION_MESSAGE);

            // posibilitatea de a continua jocul !! dar mai trebe lucrat pentru ca nu pot modifica pe poza:)
            frame.drawingPanel.repaint();
        } catch (IOException ex) {
            // exceptie cand fisierul nu poate fi deschis
            JOptionPane.showMessageDialog(frame, "Error loading image: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setSaveBtn(ActionEvent e) {
        // crearea fisierului pentru imagine
        BufferedImage image = new BufferedImage(frame.drawingPanel.getWidth(), frame.drawingPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        //pune drawingPanelul in fisier
        frame.drawingPanel.paint(g2d);
        //g2d.dispose();

        //file path
        String filePath = "board.png";

        try {
            File file = new File(filePath);
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(frame, "Image saved successfully to " + filePath, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving image: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
