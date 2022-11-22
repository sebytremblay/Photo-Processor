package controller;

import java.io.File;
import java.io.IOException;

public interface Features {
  /**
   * Reads the command of a string.
   *
   * @param btnAction the action to read
   */
  void readButtonClick(String btnAction, String imgName);

  void readButtonClick(String btnAction, String filePath, String imgName);

  void takesInTextField(String btnAction, String value, String imgName);

}
