import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.ImageProcessorModel;
import model.imageprocessor.ImageProcessor;

import static utils.ImageUtils.loadInputFile;

/**
 * This class is where the ImageProcessor's main method exists that runs the program.
 */
public class RunImageProcessor {

  /**
   * Main method for the ImageProcessor.
   * @param args arguments for the main method.
   *
   */
  public static void main(String[] args) {
    ImageProcessor model = new ImageProcessorModel();
    ImageProcessorController controller;

    if (args.length > 1 && args[0].equals("file")) {
      String scriptFilePath = args[1];
      Readable input = loadInputFile(scriptFilePath);
      controller = new ImageProcessorControllerImp(input, System.out, model);
    } else {
      controller = new ImageProcessorControllerImp(model);
    }

    controller.run();
  }
}