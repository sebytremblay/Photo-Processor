package commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.imageprocessor.ImageProcessor;

/**
 * A command to load an image.
 */
public class Load extends AbstractCommand {
  private final Scanner imageFile;
  private final String imgName;

  /**
   * Creates an instance of the commands.Flip.Load command.
   *
   * @param imgPath the path of the image to load
   * @param imgName what to name the image after its loaded
   * @param append place to informative information about success of command
   */
  public Load(String imgPath, String imgName,Appendable append) {
    super(append);
    this.imgName = imgName;
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(imgPath));
    }
    catch (FileNotFoundException e) {
      sc = new Scanner("");
      super.successMessage("File "+imgPath+ " not found!");
    }

    this.imageFile = sc;
  }

  @Override
  public void go(ImageProcessor model) {
    model.loadASCIIPPM(this.imageFile, this.imgName);
    super.successMessage("Load");
  }

}
