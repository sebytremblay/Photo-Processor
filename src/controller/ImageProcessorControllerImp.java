package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ColorTransformationCommand;
import controller.commands.DisplayComponent;
import controller.commands.Flip;
import controller.commands.KernelCommand;
import controller.commands.Load;
import controller.commands.ProcessCommand;
import controller.commands.Save;
import model.imageprocessor.ImageProcessor;
import model.operations.VisualizeBlue;
import model.operations.VisualizeBrighten;
import model.operations.FlipImage;
import model.operations.VisualizeGreen;
import model.operations.VisualizeIntensity;
import model.operations.VisualizeLuma;
import model.operations.VisualizeRed;
import model.operations.VisualizeValue;

/**
 * Is the controller of the ImageProcessor that takes in commands and executes them on the model.
 */
public class ImageProcessorControllerImp extends AbstractController implements ImageProcessorController {
  private final Readable input;
  /**
   * Is a constructor for the controller. It takes in a model, and sets the readable and appendable
   * to a default value
   *
   * @param processor the model of the controller
   */
  public ImageProcessorControllerImp(ImageProcessor processor) {
    this(new InputStreamReader(System.in), System.out, processor);
  }

  /**
   * A constructor for the controller. It takes in a model, and sets the readable and appendable
   * to a default value
   *
   * @param input     how the controller reads input
   * @param output    how the controller reads output
   * @param processor the image processor
   */
  public ImageProcessorControllerImp(Readable input, Appendable output, ImageProcessor processor) {
    super(processor);
    if (input == null || output == null || processor == null) {
      throw new IllegalArgumentException("Readable, Appendable, and ImageProcessor "
              + "all must not be" + "null");
    }
    this.input = input;
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    while (scan.hasNext()) {
      String input = scan.next();
      Function<Scanner, ProcessCommand> cmd = this.commands.getOrDefault(input, null);
      if (cmd == null) {
        error("Command not recognized");
      } else {
        try {
          ProcessCommand c = cmd.apply(scan);
          c.run(this.model);
        } catch (NoSuchElementException e) {
          error("Insufficient arguments");
        } catch (IllegalArgumentException e) {
          error("Invalid arguments.");
        }
      }
    }
  }

  @Override
  public void processCommand(String btnAction) {
    Scanner scan = new Scanner(btnAction);
    Function<Scanner, ProcessCommand> cmd = this.commands.getOrDefault(scan.next(),
            null);
    try {
      ProcessCommand c = cmd.apply(scan);
      c.run(this.model);
    } catch (NoSuchElementException e) {
      error("Insufficient arguments");
    } catch (IllegalArgumentException e) {
      error("Invalid arguments.");
    }
  }

}
