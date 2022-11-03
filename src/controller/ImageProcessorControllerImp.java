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
import model.operations.visualizeBlue;
import model.operations.visualizeGreen;
import model.operations.visualizeIntensity;
import model.operations.visualizeLuma;
import model.operations.visualizeRed;
import model.operations.visualizeValue;

public class ImageProcessorControllerImp implements ImageProcessorController {
  private final Readable input;
  private final Appendable output;
  private final ImageProcessor processor;
  private final Map<String, Function<Scanner, ProcessCommand>> commands;

  public ImageProcessorControllerImp(ImageProcessor processor) {
    this(new InputStreamReader(System.in), System.out, processor);
  }

  public ImageProcessorControllerImp(Readable input, Appendable output, ImageProcessor processor) {
    if (input == null || output == null || processor == null){
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
            s -> new Save(s.next(), s.next(),output));
    commands.put("red-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeRed(),output));
    commands.put("blue-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeBlue(),output));
    commands.put("green-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeGreen(),output));
    commands.put("value-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeValue(),output));
    commands.put("intensity-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeIntensity(),output));
    commands.put("luma-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeLuma(),output));
    commands.put("horizontal-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Horizontal,output));
    commands.put("vertical-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Vertical,output));
    commands.put("brighten",
            s -> new Brighten(s.next(), s.next(), s.next(),output));
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);

    while (scan.hasNext()) {
      String input = scan.next();

      Function<Scanner, ProcessCommand> cmd = this.commands.getOrDefault(input, null);

      if (cmd == null) {
        invalidCommandError();
      } else {
        try {
          ProcessCommand c = cmd.apply(scan);
          c.go(this.processor);
        } catch (NoSuchElementException e) {
          insufficientArgumentsError();
        }catch (IllegalArgumentException e){
          invalidArgumentsError();
        }
      }
    }
  }

  // Notifies the user invalid arguments
  private void insufficientArgumentsError() {
    try {
      this.output.append("Insufficient arguments.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
  private void invalidArgumentsError() {
    try {
      this.output.append("Invalid arguments.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  // Notifies the user of an invalid input
  private void invalidCommandError() {
    try {
      this.output.append("Command is not recognized.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
