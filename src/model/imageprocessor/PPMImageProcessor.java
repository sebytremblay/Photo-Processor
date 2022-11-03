package model.imageprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
   * Constructor the Image Processor with no laaded Images.
   */
  public PPMImageProcessor() {
    // INVARIANT: loadedImages size is always equal to the loadedImagesMaxValue size
    loadedImages = new HashMap<String, Pixel[][]>();
    loadedImagesMaxValue = new HashMap<String, Integer>();
  }

  /**
   * Loads an image from an ASCII PPM file. If imgName is already taken,
   * the new image will overwrite the old image.
   *
   * @param sc      the image loaded into the file
   * @param imgName the name of the generated image
   */
  @Override
  public void loadASCIIPPM(Scanner sc, String imgName) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.equals("") && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixelGrid = new Pixel[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new RGBPixel(r, g, b, maxValue);
        pixelGrid[row][col] = pixel;
      }
    }
    loadedImages.put(imgName, pixelGrid);
    loadedImagesMaxValue.put(imgName, maxValue);
  }

  /**
   * Visualizes a component based on the given function.
   *
   * @param imgName      name of the loaded image we are trying to manipulate
   * @param f            function that is applied the image
   * @param newImageName the new modified name in the processor
   */
  @Override
  public void visualize(String imgName, String newImageName, Function f) {
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

    loadedImages.put(newImageName, newPixelGrid);
    loadedImagesMaxValue.put(newImageName, maxValue);
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
   * @param newImageName the name of the newly flipped image
   * @param dir          the direction to flip
   */
  @Override
  public void flipImage(String imgName, String newImageName, Direction dir) {
    isLoadedImgName(imgName);
    Pixel[][] pixelGrid = loadedImages.get(imgName);

    Pixel[][] newPixelGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    if (dir == ImageProcessor.Direction.Vertical) {
      int rowCounter = 0;
      for (int row = newPixelGrid.length - 1; row >= 0; row -= 1) {
        for (int col = 0; col < newPixelGrid[row].length; col += 1) {
          newPixelGrid[rowCounter][col] = pixelGrid[row][col];
        }
        rowCounter += 1;
      }
    } else {
      int colCounter = 0;
      for (int row = 0; row < newPixelGrid.length; row += 1) {
        for (int col = newPixelGrid[row].length - 1; col >= 0; col -= 1) {
          newPixelGrid[row][colCounter] = pixelGrid[row][col];
          colCounter += 1;
        }
        colCounter = 0;
      }
    }
    loadedImages.put(newImageName, newPixelGrid);
    loadedImagesMaxValue.put(newImageName, loadedImagesMaxValue.get(imgName));
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
    loadedImages.put(newImgName, newPixelGrid);
    loadedImagesMaxValue.put(newImgName, maxValue);
  }

  /**
   * Saves an image to a given location and a given name.
   * If filePath is occupied it will overwrite.
   *
   * @param filePath location to save of an image
   * @param imgName  name of the saved image
   */
  @Override
  public void saveImage(String filePath, String imgName) {
    StringBuilder result = getImageString(imgName);
    result.deleteCharAt(result.length() - 1);
    try {
      Files.writeString(Path.of(filePath), result);
    } catch (IOException e) {
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }


  @Override
  public StringBuilder getImageString(String imgName) {
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
    return result;
  }

  private void writeMessage(String message, StringBuilder builder) {
    builder.append(message + "\n");
  }
}
