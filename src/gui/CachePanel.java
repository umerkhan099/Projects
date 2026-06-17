package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CachePanel extends JPanel {

    private final JLabel titleLabel;
    private final JLabel cacheLabel;
    private final JLabel hitsLabel;
    private final JLabel missesLabel;

    public CachePanel(String title) {

        setLayout(new GridLayout(4, 1));

        titleLabel = new JLabel(title, SwingConstants.CENTER);
        cacheLabel = new JLabel("[]", SwingConstants.CENTER);
        hitsLabel = new JLabel("Hits: 0", SwingConstants.CENTER);
        missesLabel = new JLabel("Misses: 0", SwingConstants.CENTER);

        add(titleLabel);
        add(cacheLabel);
        add(hitsLabel);
        add(missesLabel);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void updateData(
            List<Integer> cache,
            int hits,
            int misses) {

        cacheLabel.setText(cache.toString());
        hitsLabel.setText("Hits: " + hits);
        missesLabel.setText("Misses: " + misses);
    }
}