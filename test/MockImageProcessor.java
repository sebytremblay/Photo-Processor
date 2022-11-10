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
    log.append("loaded image: " + imgName + "\n");
  }

  @Override
  public void visualize(String imgName, String newImageName, Function f) {
    log.append("imgName: " + imgName + ", newImageName:" + newImageName + "");
  }

  @Override
  public void flipImage(String imgName, String newImageName, Direction dir) {
    if (dir == Direction.Vertical) {
      log.append("imgName: " + imgName + ", newImageName:" + newImageName + ", " +
              "Direction: Vertical");
    } else {

      log.append("imgName: " + imgName + ", newImageName:" + newImageName + ", " +
              "Direction: Horizontal");
    }
  }

  @Override
  public void brighten(String imgName, String newImgName, int brightenBy) {
    log.append("imgName: " + imgName + ", newImageName:" + newImgName + ", " +
            "brightenBy: " + brightenBy);
  }

  @Override
  public String getImageAsString(String imgName) {
    log.append("imgName " + imgName);
    return "";
  }

  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    log.append("imgName: "+imgName);
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
  public void applyColorTransformation(String imgName, String newImgName, double[][] transformation) {
    String transType = "unrecognized";

    if (transformation[0][0] == 0.2126) {
      transType = "greyscale";
    } else if (transformation[0][0] == 0.393) {
      transType = "sepia";
    }

    log.append("Applied " + transType + " color transformation to: " + imgName);
  }
}
