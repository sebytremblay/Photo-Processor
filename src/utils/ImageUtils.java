package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
  public static String readPPM(String filePath) {
    StringBuilder builder = new StringBuilder();
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("");
    }

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
    StringBuilder build = new StringBuilder();
    build.append("P3" + "\n");
    build.append(width + "\n");
    build.append(height + "\n");
    build.append(maxValue + "\n");
    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        build.append(r + "\n");
        build.append(g + "\n");
        build.append(b + "\n");

      }
    }
    return build.toString();
  }

  public static String readImageIO(String filePath) {
    StringBuilder builder = new StringBuilder();
    BufferedImage file;

    try {
      file = ImageIO.read(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    } catch (IOException e) {
      throw new IllegalArgumentException("File cannot be read");
    }

    // Add width, height, max value
    builder.append("PNG \n");
    builder.append(file.getWidth() + " " + file.getHeight() + "\n");
    builder.append(255 + "\n");

    for (int row = 0; row < file.getHeight(); row++) {
      for (int col = 0; col < file.getWidth(); col++) {
        int binaryPixel = file.getRGB(col, row);

        int blue = binaryPixel & 0xff;
        int green = (binaryPixel & 0xff00) >> 8;
        int red = (binaryPixel & 0xff0000) >> 16;

        builder.append(red + "\n");
        builder.append(green + "\n");
        builder.append(blue + "\n");
      }
    }

    return builder.toString();
  }

  /**
   * Saves an Image a designated file path.
   *
   * @param filePath the file path where the image is stored
   * @param img      the img in which is being stored
   */
  public static void savePPM(String filePath,String img){
    try {
      Files.writeString(Path.of(filePath), img);
    } catch (IOException e) {
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }
  public static void saveIOFile(String filePath,BufferedImage bufferedImage){
    String fileType = filePath.substring(filePath.indexOf(".")+1);
    try {
      ImageIO.write(bufferedImage,fileType,new File(filePath));
    } catch (IOException e) {
      throw new IllegalArgumentException("the filePath you are saving to is not valid");
    }
  }

}
