package commands;

import model.imageprocessor.ImageProcessor;

public interface ProcessCommand {
  /**
   * Performs the command on the model.
   *
   * @param model the model to execute the command on
   */
  void go(ImageProcessor model);
}
