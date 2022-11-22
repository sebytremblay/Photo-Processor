import controller.ControllerFeaturesImpl;
import model.imageprocessor.ImageProcessorModel;
import model.imageprocessor.ImageProcessor;
import view.ImageProcessorGUI;
import view.SwingGUIView;


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
    ImageProcessorGUI view = new SwingGUIView();
    ControllerFeaturesImpl controller = new ControllerFeaturesImpl(view, model);
  }
}