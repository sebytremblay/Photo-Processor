package controller.commands;

import model.imageprocessor.ImageProcessor;

/**
 * Represents the interface for a command that the controller of the image processor will apply.
 */
public interface ProcessCommand {
  /**
   * Performs the command on the model.
   *
   * @param model the model to execute the command on
   */
  void run(ImageProcessor model);
}
