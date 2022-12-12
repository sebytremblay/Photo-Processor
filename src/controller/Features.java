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
  void readButtonClick(String btnAction, String maskName, String imgName);

  void readButtonClickSaveName(String btnAction, String maskName, String imgName, String newImgName);

  /**
   * Reads a button click that uses a file path.
   *
   * @param btnAction the button action
   * @param filePath  the file path for the action
   * @param imgName   the image to alter
   */
  void readButtonClickFile(String btnAction, String filePath, String imgName);

  /**
   * Reads a button click that uses an input value.
   *
   * @param btnAction the button click
   * @param value     the input value
   * @param imgName   the image to alter
   * @param newImgName the new image name you are saving
   */
  void takesInTextField(String btnAction, String value, String mask, String imgName,String newImgName);


  /**
   * creates a mask to exist in the ImageProcessor
   * @param currImgName the currentImage name that this will applied to
   * @param topLeftRow top left row of where the mask starts
   * @param topLeftCol top left col of where the mask start
   */
  void createMask(String currImgName, String maskName,int topLeftRow, int topLeftCol);

}
