package utils;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.pixel.Pixel;
import model.pixel.RGBPixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method 
 *  as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param imgPath the path of the file.
   */
  public static Pixel[][] loadPPM(String imgPath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(imgPath));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException();
    }
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
    int cols = sc.nextInt();
    int rows = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixelGrid = new Pixel[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col <cols; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new RGBPixel(r, g, b, maxValue);
        pixelGrid[row][col] = pixel;
      }
    }
  return pixelGrid;
  }

  public static int getMaxValue(String imgPath){

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(imgPath));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException();
    }
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
    sc.nextInt();
    sc.nextInt();
    int maxValue = sc.nextInt();

    return maxValue;
  }
  //demo main
  public static void main(String []args) {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "Koala.ppm";
      }
      
      ImageUtil.loadPPM(filename);
  }
}

