package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.ProcessCommand;
import controller.commands.ColorTransformationCommand;
import controller.commands.DisplayComponent;
import controller.commands.Flip;
import controller.commands.KernelCommand;
import model.imageprocessor.ImageProcessor;
import model.operations.FlipImage;
import model.operations.VisualizeBlue;
import model.operations.VisualizeBrighten;
import model.operations.VisualizeGreen;
import model.operations.VisualizeIntensity;
import model.operations.VisualizeLuma;
import model.operations.VisualizeRed;
import model.operations.VisualizeValue;

/**
 * Represents the abstracted controller for the view based and script based controllers for
 * the image processor.
 */
public class AbstractController {
  protected final Map<String, Function<Scanner, ProcessCommand>> commands;
  protected Appendable output;
  protected ImageProcessor model;

  /**
   * Controller for the abstract command.
   *
   * @param model model that exists as the source of truth for the image processor.
   */
  public AbstractController(ImageProcessor model, Appendable output) {
    this.output = output;
    this.model = model;
    this.commands = new HashMap<String, Function<Scanner, ProcessCommand>>();
    double[][] blurKernel =
        {{1.0 / 16, 1.0 / 8, 1.0 / 16},
        {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}};
    double[][] sharpenKernel =
        {{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
        {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}};
    double[][] greyscaleTrans =
        {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
    double[][] sepiaTrans =
        {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

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

  // error logging
  protected void error(String message) {
    try {
      this.output.append(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("The state is incorrect");
    }
  }

  /**
   * Applies a command on the model.
   *
   * @param command the string to write
   */
  protected void processCommand(String command) {
    Scanner scan = new Scanner(command);
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
