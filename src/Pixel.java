import java.util.function.Function;

//
public interface Pixel {

 /**
  * Visualizes the given color representation the pixel.
  * @Param func takes a function that visualizes
  * @return the visualized pixel
  */
 Pixel visual(Function<Pixel,Integer> func);

 /**
  * Gets the list of all the components of this pixel.
  * @return the list of pixel components
  */
 int[] getComponents();

 /**
  * Changes the image brightness by a given factor -Will not brighten if new values are out of range.
  * @param factor amount of brightness to add or reduce to pixel
  * @return the new pixel brightened.
  */
 Pixel brightenPixel(int factor);
}
