package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * GUI For the Image Processor.
 */
public interface ImageProcessorGUI extends FeaturesGUI {
  /**
   * Sets an image to display in the GUI.
   *
   * @param img the new image to load
   */

  void setImage(BufferedImage img);

  /**
   * Sets the displayed histogram.
   *
   * @param histogram the new histogram to display
   */
  void setHistogram(int[][] histogram);

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated, and therefore it must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed
   */
  void renderMessage(String message);

}
