package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * An Utilities class that exists for PPM images.
 */
public class ImageUtils {

  /**
   * Reads and stores the PPM image from a specific filePath.
   *
   * @param filePath the filePath where the ppm is
   * @return the stored PPM image represented as a string.
   */
  public static Pixel[][] readPPM(String filePath) {
    StringBuilder builder = new StringBuilder();
    Scanner sc;

    // Finds file
    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file path");
    }

    // Read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.equals("") && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // Det up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    // Check PPM type
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    // Initialize pixel grid
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixelGrid = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        // reads and normalizes each of the pixel's components
        int red = (sc.nextInt() * maxValue) / 255;
        int green = (sc.nextInt() * maxValue) / 255;
        int blue = (sc.nextInt() * maxValue) / 255;

        pixelGrid[row][col] = new RGBPixel(red, green, blue);
      }
    }

    return pixelGrid;
  }

  /**
   * Reads an IO Image and loads it into a pixel grid.
   *
   * @param filePath the path of the image to load
   * @return
   */
  public static Pixel[][] readImageIO(String filePath) {
    BufferedImage file;

    try {
      file = ImageIO.read(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    } catch (IOException e) {
      throw new IllegalArgumentException("File cannot be read");
    }

    Pixel[][] pixelGrid = new Pixel[file.getHeight()][file.getWidth()];

    for (int row = 0; row < file.getHeight(); row++) {
      for (int col = 0; col < file.getWidth(); col++) {
        int binaryPixel = file.getRGB(col, row);

        int blue = binaryPixel & 0xff;
        int green = (binaryPixel & 0xff00) >> 8;
        int red = (binaryPixel & 0xff0000) >> 16;

        pixelGrid[row][col] = new RGBPixel(red, green, blue);
      }
    }

    return pixelGrid;
  }

  /**
   * Loads an input string stored in a file.
   *
   * @param scriptFilePath the file path of the input
   * @return the input loaded into a readable
   */
  public static Readable loadInputFile(String scriptFilePath) {
    try {
      Readable file = new StringReader(Files.readString(Path.of(scriptFilePath)));
      return file;
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid file path provided");
    }
  }

  /**
   * Saves an Image a designated file path.
   *
   * @param filePath the file path where the image is stored
   * @param img      the img in which is being stored
   */
  public static void savePPM(String filePath, String img) {
    try {
      Files.writeString(Path.of(filePath), img);
    } catch (IOException e) {
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }

  /**
   * Saves an IO Image to the specified path.
   *
   * @param filePath      the file path to save to
   * @param bufferedImage the image to save
   */
  public static void saveIOFile(String filePath, BufferedImage bufferedImage) {
    String fileType = filePath.substring(filePath.indexOf(".") + 1);
    try {
      ImageIO.write(bufferedImage, fileType, new File(filePath));
    } catch (IOException e) {
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }
}
