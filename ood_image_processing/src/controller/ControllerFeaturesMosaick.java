
package controller;

import java.util.Scanner;

import controller.commands.BrightenChannels;
import controller.commands.Mosaicking;
import model.processor.ImageProcessor;
import util.Util;
import view.ImageProcessorView;

/**
 * An extension of the controller features class that also manages a view so that the view can be
 * updated every time an image is changed.
 */
public class ControllerFeaturesMosaick extends ControllerFeatures{


  /**
   * Create a new instance of gui controller features with a processor to handle image processing,
   * and a view to inform about changes to images.
   * @param processor the processor to use for image loading/saving/processing
   * @param view the view to send images to
   * @throws IllegalArgumentException if any arguments are null
   */


  /**
   * Create a new instance of controller features with a processor delegate to load images to,
   * save images from, and run commands via.
   *
   * @param processor the processor delegate
   * @throws IllegalArgumentException if the processor is null
   */
  public ControllerFeaturesMosaick(ImageProcessor processor) throws IllegalArgumentException {
    super(processor);
    this.knownCommands.put("mosaick", (Scanner sc) -> new Mosaicking(Util.requireNonNullArg(sc).nextInt()));
  }
}