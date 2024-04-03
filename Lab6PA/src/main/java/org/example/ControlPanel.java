package org.example;
import javax.swing.*;


class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        loadBtn.addActionListener(e -> loadGame());
        saveBtn.addActionListener(e -> saveGame());
        exitBtn.addActionListener(e -> exitGame());
    }

    private void exitGame() {
    }



    private void loadGame() {

    }

    private void saveGame() {

    }
}