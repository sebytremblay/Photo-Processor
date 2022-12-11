package view;

import controller.Features;

public interface FeaturesGUI {

  /**
   * Accepts a feature for the GUI.
   *
   * @param feature is the feature that is being accepted
   */
  void acceptsFeaturesObject(Features feature);
}
