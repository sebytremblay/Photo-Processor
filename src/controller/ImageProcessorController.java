package controller;

/**
 * represents the controller for the ImageProcessor.
 */
public interface ImageProcessorController {
  /**
   * Runs the processor.
   *
   * @throws IllegalStateException if the processor fails
   */
  void run() throws IllegalStateException;
}
