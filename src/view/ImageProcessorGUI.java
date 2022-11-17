package view;

import controller.Features;

/**
 * GUI For the Image Processor
 */
public interface ImageProcessorGUI {

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   */
  void refresh();
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
