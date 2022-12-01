package controller;

import model.processor.ImageProcessor;
import view.ImageProcessorView;

public class GuiControllerFeaturesMosaick extends ControllerFeaturesMosaick{

  /**
   * Create a new instance of controller features with a processor delegate to load images to,
   * save images from, and run commands via.
   *
   * @param processor the processor delegate
   * @throws IllegalArgumentException if the processor is null
   */
  public GuiControllerFeaturesMosaick(ImageProcessor processor, ImageProcessorView view) throws IllegalArgumentException {
    super(processor);
  }
}
