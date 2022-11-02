import java.util.function.Function;

/**
 * A command to visualize a component an image.
 */
public class DisplayComponent implements ProcessCommand {
  private final String imgPath;
  private final String imgName;
  private final Function<Pixel, Integer> func;

  /**
   * Creates an instance of the DisplayComponent command.
   *
   * @param imgPath the path of the image
   * @param imgName what to name the image after its visualized
   * @param func    how to visualize the image
   */
  public DisplayComponent(String imgPath, String imgName, Function<Pixel, Integer> func) {
    this.imgPath = imgPath;
    this.imgName = imgName;
    this.func = func;
  }

  @Override
  public void go(ImageProcessor model) {
    model.visualize(this.imgName, this.imgPath, func);
  }
}


