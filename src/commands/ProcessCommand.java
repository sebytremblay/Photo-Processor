package commands;

import model.imageprocessor.ImageProcessor;

/**
 * Interface for a command our image processor handles.
 */
public interface ProcessCommand {
  /**
   * Performs the command on the model.
   *
   * @param model the model to execute the command on
   */
  void run(ImageProcessor model);
}
