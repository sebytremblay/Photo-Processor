package view;

import java.awt.*;

import javax.swing.*;

public class Histogram extends JPanel {
  private int[][] histograms;


  public Histogram() {
    super();
    histograms = new int[4][256];
    this.setMinimumSize(new Dimension(500,500));
  }

  public void setHistograms(int[][] histograms) {
    this.histograms = histograms;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.scale(1, -1);
    g2d.translate(0, -getHeight());
    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.PINK};
    for (int kind = 0; kind < histograms.length; kind += 1) {
      g2d.setColor(colors[kind]);
      int maxValue = this.getMaxValue(histograms[kind]);
      for (int xComponent = 0; xComponent < histograms[0].length - 1; xComponent += 1) {
        g2d.drawLine(xComponent, (int)((histograms[kind][xComponent] / (double) maxValue) * 200),
                (xComponent + 1), (int)((this.histograms[kind][xComponent + 1] / (double)maxValue) * 200));
      }
    }
  }

  private int getMaxValue(int[] histograms) {
    int max = -1;
    for (int val : histograms) {
      max = Math.max(max, val);
    }
    return max;
  }
}
