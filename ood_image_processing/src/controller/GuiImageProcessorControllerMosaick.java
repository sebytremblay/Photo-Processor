package controller;

import model.processor.ImageProcessor;
import util.Util;
import view.ImageProcessorView;

public class GuiImageProcessorControllerMosaick implements ImageProcessorController{
  private final ImageProcessorView view;
  private final ControllerFeatureSet controls;
  public GuiImageProcessorControllerMosaick(ImageProcessor processor, ImageProcessorView view)
          throws IllegalArgumentException {
    this.view = Util.requireNonNullArg(view);
    this.controls = new GuiControllerFeaturesMosaick(Util.requireNonNullArg(processor), view);
  }

  public GuiImageProcessorControllerMosaick(ImageProcessorView view, ControllerFeatureSet controls) throws IllegalArgumentException {
    this.view = Util.requireNonNullArg(view);
    this.controls = Util.requireNonNullArg(controls);
  }

  @Override
  public void run() throws IllegalStateException {
    view.setActionObject(this.controls);

  }
}
