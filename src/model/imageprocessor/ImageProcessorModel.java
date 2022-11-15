package model.imageprocessor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.operations.VisualizeIntensity;
import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * Represents a model for an Image Processor.
 */
public class ImageProcessorModel implements ImageProcessor {
  private final Map<String, Pixel[][]> loadedImages;

  /**
   * Constructor the Image Processor with no loaded Images.
   */
  public ImageProcessorModel() {
    loadedImages = new HashMap<String, Pixel[][]>();
  }

  @Override
  public void load(Pixel[][] pixelGrid, String imgName) {
    loadedImages.put(imgName, pixelGrid);
  }

  @Override
  public void visualize(String imgName, String newImgName, Function<Pixel, Pixel> f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[0].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        Pixel newPixel = f.apply(pixel);
        newPixelGrid[row][col] = newPixel;
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  // determines is an image is loaded, and throws an error if not
  private void isLoadedImgName(String imgName) {
    if (!loadedImages.containsKey(imgName)) {
      throw new IllegalArgumentException("The image " + imgName
              + " is not loaded in the processor");
    }
  }

  @Override
  public void flipImage(String imgName, String newImgName, Function<Pixel[][], Pixel[][]> f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    loadedImages.put(newImgName, f.apply(pixelGrid));
  }


  @Override
  public String getImageAsString(String imgName) {
    isLoadedImgName(imgName);
    StringBuilder result = new StringBuilder();
    Pixel[][] saveImage = loadedImages.get(imgName);
    writeMessage("P3", result);
    writeMessage(saveImage[0].length + " " + saveImage.length, result);
    writeMessage("255", result);

    for (int row = 0; row < saveImage.length; row += 1) {
      for (int col = 0; col < saveImage[0].length; col += 1) {
        Pixel pixel = saveImage[row][col];
        writeMessage(pixel.toString(), result);
      }
    }

    return result.deleteCharAt(result.length() - 1).toString();
  }

  @Override
  public BufferedImage getImageAsBufferedImage(String imgName) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    BufferedImage bufferedImage = new BufferedImage(pixelGrid[0].length,
            pixelGrid.length, 1);
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        bufferedImage.setRGB(col, row, pixelGrid[row][col].PixelToHex());
      }
    }
    return bufferedImage;
  }


  @Override
  public void applyKernel(String imgName, String newImgName, double[][] kernel) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        Pixel[][] kernelBackground = getKernelBackground(row, col, kernel.length,
                pixelGrid);
        newPixelGrid[row][col] = pixelGrid[row][col].kernelEval(kernel, kernelBackground);
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  @Override
  public void applyColorTransformation(String imgName, String newImgName, double[][] transformation) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        newPixelGrid[row][col] = pixelGrid[row][col].colorTransformation(transformation);
      }
    }
    loadedImages.put(newImgName, newPixelGrid);
  }

  @Override
  public Map<Integer, Integer> generateHistogram(String imgName, HistogramOptions histogramOptions) {
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Map<Integer, Integer> histogramMap = new HashMap<Integer, Integer>();
    for (int row = 0; row < pixelGrid.length; row += 1) {
      for (int col = 0; col < pixelGrid[0].length; col += 1) {
        Pixel pix = pixelGrid[row][col];
        switch (histogramOptions) {
          case Red:
            histogramMap.put(pix.getComponents()[0], histogramMap.getOrDefault(pix.getComponents()[0], 0) + 1);
            break;
          case Green:
            histogramMap.put(pix.getComponents()[1], histogramMap.getOrDefault(pix.getComponents()[1], 0) + 1);
            break;
          case Blue:
            histogramMap.put(pix.getComponents()[2], histogramMap.getOrDefault(pix.getComponents()[2], 0) + 1);
            break;
          case Intensity:
            Pixel intensityPixel = new VisualizeIntensity().apply(pix);
            histogramMap.put(intensityPixel.getComponents()[0], histogramMap.getOrDefault(intensityPixel.getComponents()[0], 0) + 1);
            break;
          default:
            throw new IllegalStateException("Invalid State");
        }
      }
    }
    return histogramMap;
  }

  // Gets the values that will exist behind a particular kernel at a particular position. If a
  // particular surrounding pixel do not exist, the returned pixel will be zero
  private Pixel[][] getKernelBackground(int row, int col, int length,
                                        Pixel[][] pixelGrid) {
    Pixel[][] background = new Pixel[length][length];
    int rowCounter = 0;
    for (int r = row - length / 2; r <= row + length / 2; r += 1) {
      int colCounter = 0;
      for (int c = col - length / 2; c <= col + length / 2; c += 1) {
        if (r < 0 || c < 0 || r >= pixelGrid.length || c >= pixelGrid[0].length) {
          background[rowCounter][colCounter] = new RGBPixel(0, 0, 0);
        } else {
          background[rowCounter][colCounter] = pixelGrid[r][c];
        }
        colCounter += 1;
      }
      rowCounter += 1;
    }
    return background;
  }


  // Writes a given message in a given builder and adds a new line
  private void writeMessage(String message, StringBuilder builder) {
    builder.append(message + "\n");
  }

}
