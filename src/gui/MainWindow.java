package gui;

import simulation.CacheSimulator;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CacheSimulator simulator;

    private final CachePanel fifoPanel;
    private final CachePanel lruPanel;
    private final CachePanel lfuPanel;

    private JTextField sequenceField;
    private JTextField cacheSizeField;

    private JLabel currentAccessLabel;
    private JLabel stepLabel;

    public MainWindow() {

        setTitle("Cache Simulator");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==========================
        // Input Panel
        // ==========================

        JPanel inputPanel = new JPanel();

        cacheSizeField = new JTextField("3", 5);

        sequenceField =
                new JTextField(
                        "1 2 3 1 4 5 1 2 3",
                        25);

        JButton startButton =
                new JButton("Start");

        JButton nextButton =
                new JButton("Next Step");

        JButton runAllButton =
                new JButton("Run All");

        currentAccessLabel =
                new JLabel("Current Access: -");

        stepLabel =
                new JLabel("Step: 0 / 0");

        inputPanel.add(
                new JLabel("Cache Size:"));

        inputPanel.add(cacheSizeField);

        inputPanel.add(
                new JLabel("Sequence:"));

        inputPanel.add(sequenceField);

        inputPanel.add(startButton);
        inputPanel.add(nextButton);
        inputPanel.add(runAllButton);

        inputPanel.add(currentAccessLabel);
        inputPanel.add(stepLabel);

        add(inputPanel, BorderLayout.NORTH);

        // ==========================
        // Cache Panels
        // ==========================

        JPanel cacheContainer =
                new JPanel(
                        new GridLayout(1, 3));

        fifoPanel =
                new CachePanel("FIFO");

        lruPanel =
                new CachePanel("LRU");

        lfuPanel =
                new CachePanel("LFU");

        cacheContainer.add(fifoPanel);
        cacheContainer.add(lruPanel);
        cacheContainer.add(lfuPanel);

        add(cacheContainer,
                BorderLayout.CENTER);

        // ==========================
        // Button Logic
        // ==========================

        startButton.addActionListener(e -> {

            int cacheSize =
                    Integer.parseInt(
                            cacheSizeField.getText());

            String[] parts =
                    sequenceField
                            .getText()
                            .split("\\s+");

            int[] sequence =
                    new int[parts.length];

            for (int i = 0; i < parts.length; i++) {

                sequence[i] =
                        Integer.parseInt(parts[i]);
            }

            simulator =
                    new CacheSimulator(
                            sequence,
                            cacheSize);

            refreshPanels();
        });

        nextButton.addActionListener(e -> {

            if (simulator != null) {

                simulator.nextStep();

                refreshPanels();
            }
        });

        runAllButton.addActionListener(e -> {

            if (simulator != null) {

                simulator.runAll();

                refreshPanels();
            }
        });

        setVisible(true);
    }

    private void refreshPanels() {

        fifoPanel.updateData(
                simulator.getFIFO().getCacheContents(),
                simulator.getFIFO().getHits(),
                simulator.getFIFO().getMisses());

        lruPanel.updateData(
                simulator.getLRU().getCacheContents(),
                simulator.getLRU().getHits(),
                simulator.getLRU().getMisses());

        lfuPanel.updateData(
                simulator.getLFU().getCacheContents(),
                simulator.getLFU().getHits(),
                simulator.getLFU().getMisses());

        currentAccessLabel.setText(
                "Current Access: "
                        + simulator.getCurrentAccess());

        stepLabel.setText(
                "Step: "
                        + simulator.getCurrentIndex()
                        + " / "
                        + simulator.getTotalSteps());
    }
}