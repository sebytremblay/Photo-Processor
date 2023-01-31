import controller.ControllerFeaturesImpl;
import controller.Features;
import controller.ImageProcessorController;
import controller.ImageProcessorControllerImp;
import model.imageprocessor.ImageProcessorModel;
import model.imageprocessor.ImageProcessor;
import view.ImageProcessorGUI;
import view.SwingGUIView;

import static utils.ImageUtils.loadInputFile;


/**
 * This class is where the ImageProcessor's main method exists that runs the program.
 */
public class RunImageProcessor {

  /**
   * Main method for the ImageProcessor.
   *
   * @param args arguments for the main method.
   */
  public static void main(String[] args) {
    ImageProcessor model = new ImageProcessorModel();

    if (args.length > 1 && args[0].equals("-file")) {
      String scriptFilePath = args[1];
      Readable input = loadInputFile(scriptFilePath);
      ImageProcessorController fileController =
              new ImageProcessorControllerImp(input, System.out, model);
      fileController.run();
    } else if (args.length == 1 && args[0].equals("-text")) {
      ImageProcessorController textController = new ImageProcessorControllerImp(model);
      textController.run();
    } else if (args.length == 0) {
      ImageProcessorGUI view = new SwingGUIView();
      Features guiController = new ControllerFeaturesImpl(view, model);
    }
  }
}