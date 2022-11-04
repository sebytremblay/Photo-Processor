package model.imageprocessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * Represents an ASCII PPM Image Processor.
 */
public class PPMImageProcessor implements ImageProcessor {
  private final Map<String, Pixel[][]> loadedImages;
  private final Map<String, Integer> loadedImagesMaxValue;

  /**
   * Constructor the Image Processor with no loaded Images.
   */
  public PPMImageProcessor(){
    // INVARIANT: loadedImages size is always equal to the loadedImagesMaxValue size
    loadedImages = new HashMap<String, Pixel[][]>();
    loadedImagesMaxValue = new HashMap<String, Integer>();
  }

  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken,
   * the new image will overwrite the old image.
   *
   * @param imgAsString the image loaded into the file
   * @param imgName     the name of the generated image
   */
  @Override
  public void loadPPM(String imgAsString, String imgName) {
    Scanner scan = new Scanner(imgAsString);
    scan.next();
    int width = scan.nextInt();
    int height = scan.nextInt();
    int maxValue = scan.nextInt();
    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int row = 0; row < height; row += 1) {
      for (int col = 0; col < width; col += 1) {
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();
        pixelGrid[row][col] = new RGBPixel(r, g, b, maxValue);
      }
    }
    addPixelGridToProcessor(imgName,pixelGrid,maxValue);
  }

  /**
   * Visualizes a component based on the given function.
   *
   * @param imgName      name of the loaded image we are trying to manipulate
   * @param f            function that is applied the image
   * @param newImgName the new modified name in the processor
   */
  @Override
  public void visualize(String imgName, String newImgName, Function f) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Integer maxValue = loadedImagesMaxValue.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[0].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        Pixel newPixel = pixel.visual(f, maxValue);
        newPixelGrid[row][col] = newPixel;
      }
    }
    addPixelGridToProcessor(newImgName,newPixelGrid,maxValue);
  }

  // determines is an image is loaded, and throws an error if not
  private void isLoadedImgName(String imgName) {
    if (!loadedImages.containsKey(imgName)) {
      throw new IllegalArgumentException("The image " + imgName
              + " is not loaded in the processor");
    }
  }

  /**
   * Flips an image in the given direction.
   *
   * @param imgName      the name of the image to flip
   * @param newImgName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  @Override
  public void flipImage(String imgName, String newImgName, Direction dir) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    int maxValue = loadedImagesMaxValue.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        if (dir == Direction.Vertical) {
          newPixelGrid[newPixelGrid.length - row - 1][col] = pixelGrid[row][col];
        }
        if (dir == Direction.Horizontal){
          newPixelGrid[row][newPixelGrid[0].length - col - 1] = pixelGrid[row][col];
        }
      }
    }
    addPixelGridToProcessor(newImgName,newPixelGrid,maxValue);
  }

  /**
   * Brightens image by the provided constant and saves into new image.
   *
   * @param imgName    the name of the image to brighten
   * @param newImgName the name of the newly generated image
   * @param brightenBy the factor to brighten the image by
   */
  @Override
  public void brighten(String imgName, String newImgName, int brightenBy) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);
    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    int maxValue = loadedImagesMaxValue.get(imgName);
    for (int row = 0; row < newPixelGrid.length; row += 1) {
      for (int col = 0; col < newPixelGrid[row].length; col += 1) {
        Pixel pixel = pixelGrid[row][col];
        pixel = pixel.brightenPixel(brightenBy, maxValue);
        newPixelGrid[row][col] = pixel;
      }
    }
    addPixelGridToProcessor(newImgName,newPixelGrid,maxValue);
  }

  @Override
  public String getImageAsString(String imgName) {
    isLoadedImgName(imgName);
    StringBuilder result = new StringBuilder();
    Pixel[][] saveImage = loadedImages.get(imgName);
    Integer maxValue = loadedImagesMaxValue.get(imgName);
    writeMessage("P3", result);
    writeMessage(saveImage[0].length + " " + saveImage.length, result);
    writeMessage(String.valueOf(maxValue), result);

    for (int row = 0; row < saveImage.length; row += 1) {
      for (int col = 0; col < saveImage[0].length; col += 1) {
        Pixel pixel = saveImage[row][col];
        writeMessage(pixel.toString(), result);
      }
    }

    return result.deleteCharAt(result.length() - 1).toString();
  }

  // Writes a given message in a given builder and adds a new line
  private void writeMessage(String message, StringBuilder builder) {
    builder.append(message + "\n");
  }

  // Adds the given Image to the map based off a given ImgName, and adds a maxValue to a maxValue
  // map based off of a given ImgName
  private void addPixelGridToProcessor(String newImgName, Pixel[][] newPixelGrid, int maxValue){
    loadedImages.put(newImgName, newPixelGrid);
    loadedImagesMaxValue.put(newImgName, maxValue);

  }
}
