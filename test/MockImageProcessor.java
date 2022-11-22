import java.awt.image.BufferedImage;
import java.util.function.Function;

import model.imageprocessor.ImageProcessor;
import model.pixel.Pixel;


/**
 * Is a Mock Image processor.
 */
public class MockImageProcessor implements ImageProcessor {
  StringBuilder log;

  MockImageProcessor(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void load(Pixel[][] pixGrid, String imgName) {
    write("loaded image: " + imgName);
  }

  @Override
  public void visualize(String imgName, String newImageName, Function f) {
    write("visualize imgName: " + imgName + ", newImageName:" + newImageName + "");
  }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   */
  @Override
  public void flipImage(String imgName, String newImageName, Function<Pixel[][], Pixel[][]> func) {
    write("flipImage imgName: " + imgName + ", newImageName:" + newImageName + ", ");

  }

  @Override
  public String getImageAsString(String imgName) {
    write("Saved imgName " + imgName);
    return "";
  }

  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    write("Saved imgName: " + imgName);
    return null;
  }

  @Override
  public void applyKernel(String imgName, String newImgName, double[][] kernel) {
    String kernelType = "unrecognized";

    if (kernel[0][0] == 1.0 / 16) {
      kernelType = "blur";
    } else if (kernel[0][0] == -1.0 / 8) {
      kernelType = "sharpen";
    }

    write("Applied " + kernelType + " kernel to: " + imgName);
  }

  @Override
  public void applyColorTransformation(String imgName, String newImgName,
                                       double[][] transformation) {
    String transType = "unrecognized";

    if (transformation[0][0] == 0.2126) {
      transType = "greyscale";
    } else if (transformation[0][0] == 0.393) {
      transType = "sepia";
    }

    write("Applied " + transType + " color transformation to: " + imgName );
  }

  @Override
  public int[][] generateHistogram(String imgName) {
    write("Generated histogram.");
    return null;
  }

  private void write(String msg) {
    this.log.append(msg + "\n");
  }
}
