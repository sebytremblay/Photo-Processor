package view;

import java.awt.image.BufferedImage;
import java.util.Map;

import controller.Features;

/**
 * GUI For the Image Processor
 */
public interface ImageProcessorGUI {

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   *
   * @param newImg the new image to display
   * @param histogram the new histogram to display
   */
  void refresh(BufferedImage newImg, Map<Integer, Integer> histogram);
  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed
   */
  void renderMessage(String message);


  /** * Accepts a feature for the GUI.
   *
   * @param feature is the feature that is being accepted
   */
  void acceptsFeaturesObject(Features feature);

}
