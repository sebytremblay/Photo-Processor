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


  /**
   * Writes string to the controller's input.
   * @param btnAction the string to write
   */
  void processCommand(String btnAction);
}
