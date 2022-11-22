package controller;

import java.io.File;
import java.io.IOException;

public interface Features {
  /**
   *
   * @param btnAction the action to read.
   * @param imgName the image name that will be saved for this current image
   *
   */
  void readButtonClick(String btnAction, String imgName);

  /**
   * Reads a
   * @param btnAction
   * @param filePath
   * @param imgName
   */
  void readButtonClick(String btnAction, String filePath, String imgName);

  void takesInTextField(String btnAction, String value, String imgName);

}
