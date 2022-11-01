/**
 * A command to visualize the value component an image.
 */
public class ValueComponent implements ProcessCommand {
  private final String imgPath;
  private final String imgName;

  /**
   * Creates an instance of the ValueComponent command.
   *
   * @param imgPath the path of the image to isolate value component
   * @param imgName what to name the image after its visualized
   */
  public ValueComponent(String imgPath, String imgName) {
    this.imgPath = imgPath;
    this.imgName = imgName;
  }

  @Override
  public void go(ImageProcessor model) {
    model.visualize(this.imgName, this.imgPath, new visualizeValue());
  }
}


