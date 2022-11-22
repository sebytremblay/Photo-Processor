import java.awt.image.BufferedImage;

import controller.Features;
import view.ImageProcessorGUI;

/**
 * A mock for our GUI that does nothing when any of its methods are called.
 */
public class MockGUI implements ImageProcessorGUI {
  @Override
  public void setImage(BufferedImage img) {
    // do nothing
  }

  @Override
  public void setHistogram(int[][] histogram) {
    // do nothing
  }

  @Override
  public void refresh() {
    // do nothing
  }

  @Override
  public void renderMessage(String message) {
    // do nothing
  }

  @Override
  public void acceptsFeaturesObject(Features feature) {
    // do nothing
  }
}
