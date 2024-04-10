package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;


class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton restartButton = new JButton("Restart");
    JButton exitButton = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        this.add(saveButton);
        this.add(loadButton);
        this.add(exitButton);
        this.add(restartButton);

        saveButton.addActionListener(this::saveGame);
        loadButton.addActionListener(this::loadGame);
        exitButton.addActionListener(this::exitGame);
        restartButton.addActionListener(this::reset);

    }

    private void exitGame(ActionEvent actionEvent) {
        frame.dispose();
    }

    private void loadGame(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(loadButton);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                reset(null);
                frame.canvas.image = ImageIO.read(file);
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }
        }
    }

    private void saveGame(ActionEvent actionEvent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose the name and location for saving the game");
        int returnVal = fileChooser.showSaveDialog(saveButton);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Exportarea imaginii curente a plăcii de joc într-un fișier PNG
                ImageIO.write(frame.canvas.image, "PNG", file);
                JOptionPane.showMessageDialog(this, "Game saved successfully.", "Save Game", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                System.err.println(ex.toString());
                JOptionPane.showMessageDialog(this, "Error saving game: " + ex.getMessage(), "Save Game Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void reset(ActionEvent e) {
        frame.remove(frame.canvas);
        frame.canvas = new DrawingPanel(frame);
        frame.add(frame.canvas);
        frame.pack();
    }
}