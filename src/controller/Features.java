package controller;

/**
 * An interface for handling all the types of features in our GUi.
 */
public interface Features {
  /**
   * Reads a normal button click.
   *
   * @param btnAction the action to read
   * @param imgName   the image name that will be saved for this current image
   */
  void readButtonClick(String btnAction, String imgName);

  /**
   * Reads a button click that uses a file path.
   *
   * @param btnAction the button action
   * @param filePath  the file path for the action
   * @param imgName   the image to alter
   */
  void readButtonClick(String btnAction, String filePath, String imgName);

  /**
   * Reads a button click that uses an input value.
   *
   * @param btnAction the button click
   * @param value     the input value
   * @param imgName   the image to alter
   */
  void takesInTextField(String btnAction, String value, String imgName);

}
