import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

public class ImageProcessorControllerImp implements ImageProcessorController {
  private final Readable input;
  private final Appendable output;
  private final ImageProcessor processor;
  private final Map<String, Function<Scanner, ProcessCommand>> commands;

  public ImageProcessorControllerImp(ImageProcessor processor) {
    this(new InputStreamReader(System.in), System.out, processor);
  }

  public ImageProcessorControllerImp(Readable input, Appendable output, ImageProcessor processor) {
    this.input = input;
    this.output = output;
    this.processor = processor;

    this.commands = new HashMap<String, Function<Scanner, ProcessCommand>>();
    commands.put("load",
            s -> new Load(s.next(), s.next()));
    commands.put("save",
            s -> new Save(s.next(), s.next()));
    commands.put("red-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeRed()));
    commands.put("blue-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeBlue()));
    commands.put("green-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeGreen()));
    commands.put("value-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeValue()));
    commands.put("intensity-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeIntensity()));
    commands.put("luma-component",
            s -> new DisplayComponent(s.next(), s.next(), new visualizeLuma()));
    commands.put("horizontal-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Horizontal));
    commands.put("vertical-flip",
            s -> new Flip(s.next(), s.next(), ImageProcessor.Direction.Vertical));
    commands.put("brighten",
            s -> new Brighten(s.next(), s.next(), s.next()));
  }

  @Override
  public void run() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);

    while (scan.hasNext()) {
      String input = scan.next();

      Function<Scanner, ProcessCommand> cmd = this.commands.getOrDefault(input, null);

      if (cmd == null) {
        invalidInputError();
      } else {
        try {
          ProcessCommand c = cmd.apply(scan);
          c.go(this.processor);
        } catch (NoSuchElementException e) {
          invalidArgumentsError();
        }
      }
    }
  }

  // Notifies the user invalid arguments
  private void invalidArgumentsError() {
    try {
      this.output.append("Invalid or insufficient arguments.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  // Notifies the user of an invalid input
  private void invalidInputError() {
    try {
      this.output.append("Command is not recognized.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
