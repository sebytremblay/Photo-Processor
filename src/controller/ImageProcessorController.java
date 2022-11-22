package controller;

/**
 * Represents the controller for the ImageProcessor that performs the actions on the image.
 */
public interface ImageProcessorController {
  /**
   * Runs the processor.
   *
   * @throws IllegalStateException if the processor fails
   */
  void run() throws IllegalStateException;
}
