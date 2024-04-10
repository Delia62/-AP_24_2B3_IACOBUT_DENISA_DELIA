package org.example;

import javax.swing.*;


class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel labelSize;
    JSpinner spinnerCols;
    JSpinner spinnerRows;
    JButton createButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        labelSize = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerCols = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createButton = new JButton("Create");


        add(labelSize);
        add(spinnerRows);
        add(spinnerCols);
        add(createButton);
        createButton.addActionListener(e -> createGame());
    }

    public int getRows() {
        return (int) spinnerRows.getValue();
    }

    public int getCols() {
        return (int) spinnerCols.getValue();
    }
    private void createGame() {
        frame.remove(frame.canvas);
        frame.canvas = new DrawingPanel(frame);
        frame.add(frame.canvas);
        frame.pack();
    }
}
