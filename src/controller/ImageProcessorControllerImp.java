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
public class ImageProcessorControllerImp implements ImageProcessorController {
  private final Readable input;
  private final Appendable output;
  private final ImageProcessor processor;
  protected final Map<String, Function<Scanner, ProcessCommand>> commands;

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
    if (input == null || output == null || processor == null) {
      throw new IllegalArgumentException("Readable, Appendable, and ImageProcessor "
              + "all must not be" + "null");
    }
    this.input = input;
    this.output = output;
    this.processor = processor;
    this.commands = new HashMap<String, Function<Scanner, ProcessCommand>>();

    double[][] blurKernel = {{1.0 / 16, 1.0 / 8, 1.0 / 16},
            {1.0 / 8, 1.0 / 4, 1.0 / 8},
            {1.0 / 16, 1.0 / 8, 1.0 / 16}};
    double[][] sharpenKernel = {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
            {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
    double[][] greyscaleTrans = {{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    double[][] sepiaTrans = {{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};


    commands.put("load", s -> new Load(s.next(), s.next(), output));
    commands.put("save", s -> new Save(s.next(), s.next(), output));
    commands.put("red-component", s -> new DisplayComponent(new VisualizeRed(),
            s.next(), s.next(), output));
    commands.put("blue-component", s -> new DisplayComponent(new VisualizeBlue(), s.next(),
            s.next(), output));
    commands.put("green-component", s -> new DisplayComponent(new VisualizeGreen(), s.next(),
            s.next(), output));
    commands.put("value-component", s -> new DisplayComponent(new VisualizeValue(), s.next(),
            s.next(), output));
    commands.put("intensity-component", s -> new DisplayComponent(
            new VisualizeIntensity(), s.next(),
            s.next(), output));
    commands.put("luma-component", s -> new DisplayComponent(new VisualizeLuma(), s.next(),
            s.next(), output));
    commands.put("horizontal-flip", s -> new Flip(
            new FlipImage(ImageProcessor.Direction.Horizontal), s.next(), s.next(), output));
    commands.put("vertical-flip", s -> new Flip(
            new FlipImage(ImageProcessor.Direction.Vertical), s.next(), s.next(), output));
    commands.put("brighten", s -> new DisplayComponent(
            new VisualizeBrighten(s.nextInt()), s.next(), s.next(), output));
    commands.put("blur", s -> new KernelCommand(s.next(), s.next(),
            blurKernel, output));
    commands.put("sharpen", s -> new KernelCommand(s.next(), s.next(),
            sharpenKernel, output));
    commands.put("greyscale", s -> new ColorTransformationCommand(s.next(), s.next(),
            greyscaleTrans, output));
    commands.put("sepia", s -> new ColorTransformationCommand(s.next(), s.next(),
            sepiaTrans, output));
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
          c.run(this.processor);
        } catch (NoSuchElementException e) {
          error("Insufficient arguments");
        } catch (IllegalArgumentException e) {
          error("Invalid arguments.");
        }
      }
    }
  }

  // Notifies the user that there was an error with the input.
  private void error(String message) {
    try {
      this.output.append(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
