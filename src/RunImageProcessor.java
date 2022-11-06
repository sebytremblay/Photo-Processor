import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.ImageProcessorModel;
import model.imageprocessor.ImageProcessor;

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
    ImageProcessorController controller = new ImageProcessorControllerImp(model);
    controller.run();
  }
}