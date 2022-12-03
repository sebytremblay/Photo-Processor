package controller;

import java.util.Scanner;

import model.processor.ImageProcessor;
import util.Util;
import view.ImageProcessorView;

public class GuiControllerFeaturesMosaick extends ControllerFeaturesMosaick{

  private final ImageProcessorView view;

  /**
   * Create a new instance of gui controller features with a processor to handle image processing,
   * and a view to inform about changes to images.
   * @param processor the processor to use for image loading/saving/processing
   * @param view the view to send images to
   * @throws IllegalArgumentException if any arguments are null
   */
  public GuiControllerFeaturesMosaick(ImageProcessor processor, ImageProcessorView view)
          throws IllegalArgumentException {
    super(processor);
    this.view = Util.requireNonNullArg(view);

  }

  @Override
  public void loadFile(String path, String saveName) {
    super.loadFile(path, saveName);
    this.view.displayImage(this.processor.getImageState(saveName));
  }

  @Override
  public void runProcessingCommand(String cmd, String name, String saveName, Scanner sc) {
    super.runProcessingCommand(cmd, name, saveName, sc);
    this.view.displayImage(this.processor.getImageState(saveName));
  }
}
