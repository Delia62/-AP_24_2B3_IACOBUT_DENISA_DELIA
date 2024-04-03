package org.example;

import javax.swing.*;
class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel labelSize;
    JSpinner spinnerCols;
    JSpinner spinnerRows;
    JButton createBtn;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        labelSize = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerCols = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));


        createBtn = new JButton("Create");
        createBtn.addActionListener(e -> createGame());


        add(labelSize);
        add(spinnerRows);
        add(spinnerCols);
        add(createBtn);
    }

    private void createGame() {
        int rows = (Integer) spinnerRows.getValue();
        int cols = (Integer) spinnerCols.getValue();
        frame.canvas.init(rows,cols);
    }
}
