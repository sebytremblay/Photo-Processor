package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import commands.Brighten;
import commands.DisplayComponent;
import commands.Flip;
import commands.Load;
import commands.ProcessCommand;
import commands.Save;
import model.imageprocessor.ImageProcessor;
import model.operations.VisualizeBlue;
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
  private final Map<String, Function<Scanner, ProcessCommand>> commands;

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
      throw new IllegalArgumentException("Readable, Appendable, and ImageProcessor all must not be" +
              "null");
    }
    this.input = input;
    this.output = output;
    this.processor = processor;

    this.commands = new HashMap<String, Function<Scanner, ProcessCommand>>();
    commands.put("load",
            s -> new Load(s.next(), s.next(), output));
    commands.put("save",
            s -> new Save(s.next(), s.next(), output));
    commands.put("red-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeRed(), output));
    commands.put("blue-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeBlue(), output));
    commands.put("green-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeGreen(), output));
    commands.put("value-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeValue(), output));
    commands.put("intensity-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeIntensity(), output));
    commands.put("luma-component",
            s -> new DisplayComponent(s.next(), s.next(), new VisualizeLuma(), output));
    commands.put("horizontal-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Horizontal, output));
    commands.put("vertical-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Vertical, output));
    commands.put("brighten",
            s -> new Brighten(s.next(), s.next(), s.next(), output));
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    while (scan.hasNext()) {
      String input = scan.next();
      Function<Scanner, ProcessCommand> cmd = this.commands.getOrDefault(input, null);
      if (cmd == null) {
        error("Command Not Recognized");
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
