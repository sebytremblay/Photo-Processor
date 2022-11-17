import java.awt.image.BufferedImage;
import java.util.Map;
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
    log.append("loaded image: " + imgName + "\n");
  }

  @Override
  public void visualize(String imgName, String newImageName, Function f) {
    log.append("visualize imgName: " + imgName + ", newImageName:" + newImageName + "");
  }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImageName the name of the newly flipped image
   */
  @Override
  public void flipImage(String imgName, String newImageName, Function<Pixel[][], Pixel[][]> func) {
    log.append("flipImage imgName: " + imgName + ", newImageName:" + newImageName + ", ");

  }

  @Override
  public String getImageAsString(String imgName) {
    log.append("Saved imgName " + imgName);
    return "";
  }

  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    log.append("Saved imgName: " + imgName);
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

    log.append("Applied " + kernelType + " kernel to: " + imgName);
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

    log.append("Applied " + transType + " color transformation to: " + imgName);
  }

  @Override
  public Map<Integer, Integer> generateHistogram(String imgName, HistogramOptions histogramOptions) {
    return null;
  }
}
