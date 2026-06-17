package gui;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private final JTextArea statsArea;

    public StatisticsPanel() {

        setLayout(new BorderLayout());

        statsArea = new JTextArea();
        statsArea.setEditable(false);

        statsArea.setText("""
                FIFO
                Hits: 0  Misses: 0

                LRU
                Hits: 0  Misses: 0

                LFU
                Hits: 0  Misses: 0
                """);

        add(new JScrollPane(statsArea), BorderLayout.CENTER);
    }

    public void updateStatistics(String text) {
        statsArea.setText(text);
    }
}