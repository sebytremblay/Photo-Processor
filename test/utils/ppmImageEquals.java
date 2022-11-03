package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ppmImageEquals {
  public static boolean twoPPMStringsTheSame(String ppm1, String ppm2) {
    StringBuilder builder1 = new StringBuilder();
    Scanner sc1 = new Scanner(String.valueOf(builder1));
    while (sc1.hasNextLine()) {
      String s = sc1.nextLine();
      if (s.charAt(0) != '#') {
        builder1.append(s + System.lineSeparator());
      }
    }
    StringBuilder builder2 = new StringBuilder();
    Scanner sc2 = new Scanner(String.valueOf(builder2));
    while (sc2.hasNextLine()) {
      String s = sc2.nextLine();
      if (s.charAt(0) != '#') {
        builder2.append(s + System.lineSeparator());
      }
    }
    return builder1.equals(builder2);
  }

  /**
   * Determines if two Images are equals to each other by checking if each bit is the same
   *
   * @return if the two images are equals
   */
  public static boolean twoPPMImagesTheSame(String filePath1, String filePath2) {
    Scanner sc1;
    Scanner sc2;
    try {
      sc1 = new Scanner(new FileInputStream(filePath1));
      sc2 = new Scanner(new FileInputStream(filePath2));
    } catch (FileNotFoundException e) {
      return false;
    }
    System.out.println("test here");
    StringBuilder builder1 = new StringBuilder();
    StringBuilder builder2 = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc1.hasNextLine()) {
      String s = sc1.nextLine();
      if (s.charAt(0) != '#') {
        builder1.append(s + System.lineSeparator());
      }
    }
    while (sc2.hasNextLine()) {
      String s = sc2.nextLine();
      if (s.charAt(0) != '#') {
        builder2.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc1 = new Scanner(builder1.toString());
    sc2 = new Scanner(builder2.toString());

    String token;
    String token2;
    String val = sc1.next();
    String val2 = sc2.next();
    if (!val.equals(val2)) {
      return false;
    }
    int width = sc1.nextInt();
    int height = sc1.nextInt();
    if (width != sc2.nextInt()) {
      return false;
    }

    if (height != sc2.nextInt()) {
      return false;
    }
    if (!sc1.next().equals(sc2.next())) {
      return false;
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (sc1.nextInt() != sc2.nextInt()) {
          return false;
        }
        if (sc1.nextInt() != sc2.nextInt()) {
          return false;
        }
        if (sc1.nextInt() != sc2.nextInt()) {
          return false;
        }
      }
    }
    return true;
  }
}


